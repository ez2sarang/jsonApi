package com.fast2.zimin.navigator.service;

import com.fast2.zimin.navigator.bean.entity.Menu;
import com.fast2.zimin.navigator.bean.entity.Offer;
import com.fast2.zimin.navigator.bean.transfer.*;
import com.fast2.zimin.util.datatable.QueryModel;

import java.util.List;

/**
 * Created by ez2sarang on 15. 3. 25..
 */
public interface PresentationService {
    List<MenuInfo> getTreeRoot(Menu tree) throws Exception;

    void getMenuInfo(MenuInfoNavi navi) throws Exception;

    void getOfferList(OfferListNavi navi, QueryModel queryModel, long menuId) throws Exception;

    void getOfferInfo(OfferInfoNavi navi, Long offerId) throws Exception;

    List<RelatedOffer> getRelatedOfferList(String userId, Long offerId) throws Exception;

    List<CGInfo> getCGInfoList(Long offerId) throws Exception;

    Offer getOfferInfo(Long offerId) throws Exception;

    String getAssetType(Long offerId, Long cgId) throws Exception;

    void setSSContentInfo(SSContentInfoNavi navi) throws Exception;
}
