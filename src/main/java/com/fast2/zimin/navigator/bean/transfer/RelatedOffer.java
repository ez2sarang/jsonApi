package com.fast2.zimin.navigator.bean.transfer;

/**
 * Created by ez2sarang on 15. 5. 14..
 */
public class RelatedOffer {
    private Long offerId;
    private String offerTitle;
    private Integer offerPrice;
    private String assetType;
    private Boolean isWatchValid;
    private String rentalPeriod;

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public Boolean getIsWatchValid() {
        return isWatchValid;
    }

    public void setIsWatchValid(Boolean isWatchValid) {
        this.isWatchValid = isWatchValid;
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

    public String getRentalPeriod() {
        return rentalPeriod;
    }

    public void setRentalPeriod(String rentalPeriod) {
        this.rentalPeriod = rentalPeriod;
    }
}
