package com.fast2.zimin.navigator.bean.transfer;

/**
 * Created by ez2sarang on 15. 5. 18..
 */
public class ViewedInfo {
    private Long offerId;
    private String viewedDate;
    private String offerTitle;
    private String purchasedDate;
    private String expireDate;

    public ViewedInfo(Long offerId, String offerTitle, String expireDate, String purchasedDate, String viewedDate) {
        this.expireDate = expireDate;
        this.offerId = offerId;
        this.offerTitle = offerTitle;
        this.purchasedDate = purchasedDate;
        this.viewedDate = viewedDate;
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

    public String getViewedDate() {
        return viewedDate;
    }

    public void setViewedDate(String viewedDate) {
        this.viewedDate = viewedDate;
    }
}
