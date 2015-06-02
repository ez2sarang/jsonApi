package com.fast2.zimin.navigator.bean.transfer;

import com.fast2.zimin.navigator.config.ResultType;

import java.util.List;

/**
 * Created by ez2sarang on 15. 5. 14..
 */
public class RelatedOfferListNavi extends UserNavi {
    private List<RelatedOffer> offerList;

    public RelatedOfferListNavi() {
        super(ResultType.UNKNOWN_ERROR);
    }

    public RelatedOfferListNavi(ResultType result, String transactionToken) {
        super(result, transactionToken);
    }

    public RelatedOfferListNavi(String resultCode, String errorString) {
        super(resultCode, errorString);
    }

    public RelatedOfferListNavi(ResultType result) {
        super(result);
    }

    public List<RelatedOffer> getOfferList() {
        return offerList;
    }

    public void setOfferList(List<RelatedOffer> offerList) {
        this.offerList = offerList;
    }
}
