package com.fast2.zimin.navigator.service.impl;

import com.fast2.zimin.navigator.bean.entity.*;
import com.fast2.zimin.navigator.bean.transfer.*;
import com.fast2.zimin.navigator.config.ResultType;
import com.fast2.zimin.navigator.dao.AuthenticationDao;
import com.fast2.zimin.navigator.dao.PresentationDao;
import com.fast2.zimin.navigator.dao.RentalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.datatype.DatatypeFactory;
import java.util.Date;
import java.util.List;

/**
 * Created by ez2sarang on 15. 4. 8..
 */
@Service("rentalService")
@Transactional
public class RentalServiceImpl implements com.fast2.zimin.navigator.service.RentalService {
    @Autowired
    private PresentationDao presentationDao;

    @Autowired
    private RentalDao rentalDao;

    @Autowired
    private AuthenticationDao authenticationDao;

    @Override
    public void rentalOffer(PurchaseOfferNavi purchaseOffer) throws Exception {
        Offer offer = presentationDao.getOffer(purchaseOffer.getOfferId());
        if (null != offer) {
            NavigatorSession session = authenticationDao.getSessionByToken(purchaseOffer.getSessionToken());
            RentalOffer rentalOffer = new RentalOffer();
            long createTime = System.currentTimeMillis();
            Date licenseEndDateTime = new Date(createTime + DatatypeFactory.newInstance().newDuration(offer.getRentalPeriod()).getTimeInMillis(new Date(createTime)));
            rentalOffer.setOfferId(offer.getOfferId());
            rentalOffer.setMenuId(purchaseOffer.getSourceId());
            rentalOffer.setSubscriberId(session.getUserId());
            rentalOffer.setCreateDateTime(new Date(createTime));
            rentalOffer.setLicenseEndDateTime(licenseEndDateTime);
            rentalOffer.setPrice(purchaseOffer.getOfferPrice());
            List<CGInfo> cgList = presentationDao.getCGInfoList(purchaseOffer.getOfferId());
            for(CGInfo cg : cgList) {
                rentalOffer.setFileType(cg.getFileType());
                break;
            }
            rentalDao.save(rentalOffer);
            for(CGInfo cg : cgList) {
                rentalDao.save(new RentalContentGroup(rentalOffer.getId(), offer.getOfferId(), cg.getCgId()));
                PlayableContentGroup playableCG = new PlayableContentGroup(rentalOffer.getId(), session.getUserId(), offer.getOfferId(), cg.getCgId(), cg.getFileType(), licenseEndDateTime);
                rentalDao.save(playableCG);
                //TODO 중복체크 제외, 기간 지난 것에 대한 삭제처리는 스케쥴에서 처리함
                /*PlayableContentGroup registedPlayableCG = rentalDao.getPlayableContentGroup(playableCG);
                if(null == registedPlayableCG) {
                    rentalDao.save(playableCG);
                } else {

                }*/
            }
        }
    }

    @Override
    public void setNotifyContentPlay(NotifyContentPlayNavi navi) throws Exception {
        PlayedHistory result = rentalDao.getPlayedHistory(navi);
        if(null == result) {
            PlayedHistory entity = new PlayedHistory();
            entity.setFileType(navi.getAssetType());
            entity.setSubscriberId(navi.getUserId());
            entity.setContentGroupId(navi.getCgId());
            entity.setOffSet(navi.getOffset());
            entity.setPlayType(navi.getControlType());
            entity.setCurrentPlayDateTime(new Date());
            rentalDao.save(entity);
        } else {
            result.setOffSet(navi.getOffset());
            result.setCurrentPlayDateTime(new Date());
        }
        navi.setResultCode(ResultType.OK);
    }

    @Override
    public void setRentalOfferList(PurchasedListNavi navi) throws Exception {
        rentalDao.setRentalOfferList(navi);
    }

    @Override
    public void setViewedList(ViewedListNavi navi) throws Exception {
        rentalDao.setViewedList(navi);
    }

    @Override
    public void setAvailiableContentList(AvailiableContentListNavi navi) throws Exception {
        rentalDao.setAvailiableContentList(navi);
    }

    @Override
    public void setCheckViewAuth(CheckViewAuthorityNavi navi) throws Exception {
        boolean result = rentalDao.isExistAsset(navi.getClientId(), navi.getCgId(), navi.getAssetType(), navi.getFileName());
        navi.setResultCode(ResultType.OK, "");
        navi.setHasAuth(result);
    }
}
