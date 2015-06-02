package com.fast2.zimin.navigator.bean.transfer;

/**
 * Created by ez2sarang on 15. 5. 18..
 */
public class PurchasedInfo {
    private Long offerId;
    private String purchasedDate;
    private String offerTitle;
    private String expireDate;
    private Integer purchasedPrice;

    public PurchasedInfo(Long offerId, String offerTitle, Integer purchasedPrice, String purchasedDate, String expireDate) {
        this.expireDate = expireDate;
        this.offerId = offerId;
        this.offerTitle = offerTitle;
        this.purchasedDate = purchasedDate;
        this.purchasedPrice = purchasedPrice;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public String getOfferTitle() {
        return offerTitle;
    }

    public void setOfferTitle(String offerTitle) {
        this.offerTitle = offerTitle;
    }

    public String getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(String purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    public Integer getPurchasedPrice() {
        return purchasedPrice;
    }

    public void setPurchasedPrice(Integer purchasedPrice) {
        this.purchasedPrice = purchasedPrice;
    }
}
