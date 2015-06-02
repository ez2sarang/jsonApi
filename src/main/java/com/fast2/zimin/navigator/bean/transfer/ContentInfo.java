package com.fast2.zimin.navigator.bean.transfer;

/**
 * Created by ez2sarang on 15. 5. 18..
 */
public class ContentInfo {
    private Long offerId;
    private String purchasedDate;
    private String offerTitle;
    private String expireDate;
    private Integer offerPrice;

    public ContentInfo(Long offerId, String offerTitle, Integer offerPrice, String purchasedDate, String expireDate) {
        this.expireDate = expireDate;
        this.offerId = offerId;
        this.offerPrice = offerPrice;
        this.offerTitle = offerTitle;
        this.purchasedDate = purchasedDate;
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

    public Integer getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(Integer offerPrice) {
        this.offerPrice = offerPrice;
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
}
