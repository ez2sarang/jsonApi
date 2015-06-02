package com.fast2.zimin.navigator.bean.transfer;

import com.fast2.zimin.navigator.controller.PresentationController;
import org.apache.commons.lang.StringUtils;

/**
 * Created by ez2sarang on 15. 3. 26..
 */
public class OfferInfo {
    private Long offerId;
    private String offerTitle;
    private String genre;
    private String posterURL;
    private int offerPrice;
    private String assetType;

    public String getGenre() {
        return StringUtils.defaultString(genre);
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public int getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(int offerPrice) {
        this.offerPrice = offerPrice;
    }

    public String getOfferTitle() {
        return offerTitle;
    }

    public void setOfferTitle(String offerTitle) {
        this.offerTitle = offerTitle;
    }

    public String getPosterURL() {
        return String.format(PresentationController.POSTER_URL_FORM, PresentationController.SERVER_CONTEXT_PATH, PresentationController.POSTER_PREFIX, posterURL);
    }

    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
    }

    public String getAssetType() {
        return StringUtils.defaultString(assetType);
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }
}
