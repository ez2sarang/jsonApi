package com.fast2.zimin.navigator.service;

import com.fast2.zimin.navigator.bean.transfer.*;

/**
 * Created by ez2sarang on 15. 5. 15..
 */
public interface RentalService {
    void rentalOffer(PurchaseOfferNavi purchaseOffer) throws Exception;

    void setNotifyContentPlay(NotifyContentPlayNavi navi) throws Exception;

    void setRentalOfferList(PurchasedListNavi navi) throws Exception;

    void setViewedList(ViewedListNavi navi) throws Exception;

    void setAvailiableContentList(AvailiableContentListNavi navi) throws Exception;

    void setCheckViewAuth(CheckViewAuthorityNavi navi) throws Exception;
}
