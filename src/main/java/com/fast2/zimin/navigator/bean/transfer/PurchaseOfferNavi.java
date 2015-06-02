package com.fast2.zimin.navigator.bean.transfer;

import com.fast2.zimin.navigator.config.ResultType;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by ez2sarang on 15. 5. 15..
 */
public class PurchaseOfferNavi extends ResponseNavi {
    @JsonIgnore
    private Long offerId;
    @JsonIgnore
    private Integer offerPrice;
    @JsonIgnore
    private String sourceId;
    @JsonIgnore
    private String purchasePW;

    public PurchaseOfferNavi() {
        super(ResultType.UNKNOWN_ERROR);
    }

    public PurchaseOfferNavi(ResultType result) {
        super(result);
    }

    public PurchaseOfferNavi(ResultType result, String transactionToken) {
        super(result, transactionToken);
    }

    public PurchaseOfferNavi(String resultCode, String errorString) {
        super(resultCode, errorString);
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public Integer getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(Integer offerPrice) {
        this.offerPrice = offerPrice;
    }

    public String getPurchasePW() {
        return purchasePW;
    }

    public void setPurchasePW(String purchasePW) {
        this.purchasePW = purchasePW;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }
}
