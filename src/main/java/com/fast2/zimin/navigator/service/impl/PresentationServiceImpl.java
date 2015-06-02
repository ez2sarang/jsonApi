package com.fast2.zimin.navigator.service.impl;

import com.fast2.zimin.navigator.bean.entity.ContentAsset;
import com.fast2.zimin.navigator.bean.entity.Menu;
import com.fast2.zimin.navigator.bean.entity.Offer;
import com.fast2.zimin.navigator.bean.transfer.*;
import com.fast2.zimin.navigator.config.ResultType;
import com.fast2.zimin.navigator.dao.PresentationDao;
import com.fast2.zimin.util.DataTableObject;
import com.fast2.zimin.util.datatable.QueryModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ez2sarang on 15. 3. 25..
 */
@Service("presentationService")
@Transactional
public class PresentationServiceImpl implements com.fast2.zimin.navigator.service.PresentationService {
    @Autowired
    private PresentationDao presentationDao;

    @Override
    public List<MenuInfo> getTreeRoot(Menu tree) throws Exception {
        List<Menu> result = presentationDao.getTreesByParentId(tree.getParentId());
        List<MenuInfo> bucket = new ArrayList<>();
        if(result != null) {
            for(Menu item : result) {
                bucket.add(getMenuInfo(item));
            }
        }
        return bucket;
    }

    private MenuInfo getMenuInfo(Menu tree) throws Exception {
        MenuInfo info = new MenuInfo();
        if (null != tree) {
            info.setIsLeaf(tree.getChildren() != null && tree.getChildren().size() > 0 ? false : true);
            //TODO menu type code by enum
            info.setMenuType("05".equals(tree.getMenuType())?"LIST":"MENU");
            info.setMenuId(tree.getId());
            info.setMenuTitle(tree.getName());
        }
        return info;
    }

    @Override
    public void getMenuInfo(MenuInfoNavi navi) throws Exception {
        Menu result = presentationDao.getMenuInfo(navi.getMenuId());
        if(null == result) {
            navi.setResultCode(ResultType.NOT_FOUND);
        } else {
            navi.setIsLeaf(result.getChildren() != null && result.getChildren().size() > 0 ? false : true);
            //TODO menu type code by enum
            navi.setMenuType("05".equals(result.getMenuType()) ? "LIST" : "MENU");
            navi.setMenuTitle(result.getName());
            navi.setResultCode(ResultType.OK);
        }
    }

    @Override
    public void getOfferList(OfferListNavi navi, QueryModel queryModel, long menuId) throws Exception {
        DataTableObject result = presentationDao.getOfferList(queryModel, menuId);
        List<OfferInfo> bucket = new ArrayList<>();
        if(result.getData() != null) {
            for(Object item : result.getData()) {
                bucket.add(getOfferInfo((Offer)item));
            }
//            navi.setListCount(result.getData().size()); 요청:10 결과:8 listCount=10
        }
        navi.setOfferList(bucket);
        navi.setTotalCount((int) result.getRecordsTotal());
    }

    private OfferInfo getOfferInfo(Offer offer) throws Exception {
        OfferInfo info = new OfferInfo();
        if (null != offer) {
            info.setOfferId(offer.getOfferId());
            info.setOfferPrice(offer.getPrice());
            if(null != offer.getPcg()) {
                info.setGenre(offer.getPcg().getGenre());
                info.setOfferTitle(offer.getPcg().getTitle());
                info.setPosterURL(offer.getPcg().getPoster());
                if(null != offer.getPcg().getContentAssetList()) {
                    for(ContentAsset asset : offer.getPcg().getContentAssetList()) {
                        info.setAssetType(StringUtils.defaultString(info.getAssetType())+asset.getFileType());
                    }
                }
            }
        }
        return info;
    }

    private void getOfferDetail(Offer offer, OfferInfoNavi info) throws Exception {
        if (null != offer) {
            info.setOfferTitle(offer.getTitle());
            info.setOfferId(offer.getOfferId());
            if(null != offer.getPcg()) {
                info.setContentTitle(offer.getPcg().getTitle());
                info.setCountryOfOrigin(offer.getPcg().getCountryOfOrigin());
                info.setRunningTime(offer.getPcg().getDisplayRunTime());
                info.setReleaseDate(offer.getPcg().getYear());
                info.setRating(offer.getPcg().getRating());
                info.setGenre(offer.getPcg().getGenre());
                info.setDirector(offer.getPcg().getDirector());
                info.setActor(offer.getPcg().getActor());
                info.setSynopsis(offer.getPcg().getSummaryLong());
                info.setPosterURL(offer.getPcg().getPoster());
                if(null != offer.getPcg().getContentAssetList()) {
                    for(ContentAsset asset : offer.getPcg().getContentAssetList()) {
                        info.setAssetType(StringUtils.defaultString(info.getAssetType())+asset.getFileType());
                    }
                }
            }
        }
    }

    @Override
    public void getOfferInfo(OfferInfoNavi navi, Long offerId) throws Exception {
        try {
            Offer result = presentationDao.getOffer(offerId);
            if(null == result || null == result.getPcg()) {
                navi.setResultCode(ResultType.NOT_FOUND);
                return;
            }
            getOfferDetail(result, navi);
        } catch (Exception e) {
            e.printStackTrace();
            navi.setResultCode(ResultType.UNKNOWN_ERROR, e.getMessage());
        }
    }

    @Override
    public List<RelatedOffer> getRelatedOfferList(String userId, Long offerId) throws Exception {
        return presentationDao.getRelatedOfferList(userId, offerId);
    }

    @Override
    public List<CGInfo> getCGInfoList(Long offerId) throws Exception {
        return presentationDao.getCGInfoList(offerId);
    }

    @Override
    public Offer getOfferInfo(Long offerId) throws Exception {
        return presentationDao.getOffer(offerId);
    }

    @Override
    public String getAssetType(Long offerId, Long cgId) throws Exception {
        for(CGInfo cg : presentationDao.getCGInfoList(offerId)) {
            if(cgId.equals(cg.getCgId())) {
                return cg.getFileType();
            }
        }
        return null;
    }

    @Override
    public void setSSContentInfo(SSContentInfoNavi navi) throws Exception {
        SSContentInfoNavi result = presentationDao.getSSContentInfo(navi.getUserId(), navi.getOfferId(), navi.getCgId(), navi.getAssetType());
        if(null == result) {
            navi.setResultCode(ResultType.NOT_FOUND);
        } else {
            navi.setSsPath(String.format("%s?cgId=%s&fileType=%s&clientId=%s",result.getSsPath(), navi.getCgId(), navi.getAssetType(), navi.getUserId()));
            navi.setOffSet(result.getOffSet());
        }
    }
}
