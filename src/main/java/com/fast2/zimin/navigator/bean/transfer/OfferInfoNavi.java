package com.fast2.zimin.navigator.bean.transfer;

import com.fast2.zimin.navigator.config.ResultType;
import com.fast2.zimin.navigator.controller.PresentationController;

/**
 * Created by ez2sarang on 15. 3. 30..
 */
public class OfferInfoNavi extends ResponseNavi {

    private String contentTitle;
    private Long offerId;
    private String offerTitle;
    private String countryOfOrigin;
    private String runningTime;
    private String releaseDate;
    private String rating;
    private String genre;
    private String director;
    private String actor;
    private String synopsis;
    private String posterURL;
    private String assetType;

    public OfferInfoNavi() {
        super(ResultType.UNKNOWN_ERROR);
    }

    public OfferInfoNavi(ResultType result, String transactionToken) {
        super(result, transactionToken);
    }

    public OfferInfoNavi(String resultCode, String errorString) {
        super(resultCode, errorString);
    }

    public OfferInfoNavi(ResultType result) {
        super(result);
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenre() {
        return genre;
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(String runningTime) {
        this.runningTime = runningTime;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
}
