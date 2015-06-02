package com.fast2.zimin.navigator.bean.entity;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ez2sarang on 15. 4. 9..
 */
@Entity
public class PlayedHistory implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String subscriberId;
    private long contentGroupId;
    private String fileType;
    private String playType;
    private Double offSet;
    private Date currentPlayDateTime;
    @Formula("(select p.offerId from PlayableContentGroup p where p.subscriberId = subscriberId and p.contentGroupId = contentGroupId and p.fileType = fileType order by p.licenseEndDateTime desc limit 1)")
    private Long offerId;
    @Formula("(select o.title from Offer o where o.offerId = (select p.offerId from PlayableContentGroup p where p.subscriberId = subscriberId and p.contentGroupId = contentGroupId and p.fileType = fileType order by p.licenseEndDateTime desc limit 1))")
    private String offerTitle;
    @Formula("(select o.createDateTime from RentalOffer o where o.offerId = (select p.offerId from PlayableContentGroup p where p.subscriberId = subscriberId and p.contentGroupId = contentGroupId and p.fileType = fileType order by p.licenseEndDateTime desc limit 1) and o.subscriberId = subscriberId and o.fileType = fileType)")
    private Date purchasedDate;
    @Formula("(select p.licenseEndDateTime from PlayableContentGroup p where p.subscriberId = subscriberId and p.contentGroupId = contentGroupId and p.fileType = fileType order by p.licenseEndDateTime desc limit 1)")
    private Date expireDate;


    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "subscriberId", nullable = false, insertable = true, updatable = true, length = 32)
    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    @Basic
    @Column(name = "contentGroupId", nullable = false, insertable = true, updatable = true)
    public long getContentGroupId() {
        return contentGroupId;
    }

    public void setContentGroupId(long contentGroupId) {
        this.contentGroupId = contentGroupId;
    }

    @Basic
    @Column(name = "fileType", nullable = false, insertable = true, updatable = true, length = 10)
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Basic
    @Column(name = "playType", nullable = false, insertable = true, updatable = true, length = 50)
    public String getPlayType() {
        return playType;
    }

    public void setPlayType(String playType) {
        this.playType = playType;
    }

    @Basic
    @Column(name = "offSet", nullable = false, insertable = true, updatable = true, length = 50)
    public double getOffSet() {
        return offSet;
    }

    public void setOffSet(double offSet) {
        this.offSet = offSet;
    }

    @Basic
    @Column(name = "currentPlayDateTime", nullable = false, insertable = true, updatable = true)
    public Date getCurrentPlayDateTime() {
        return currentPlayDateTime;
    }

    public void setCurrentPlayDateTime(Date currentPlayDateTime) {
        this.currentPlayDateTime = currentPlayDateTime;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
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

    public Date getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(Date purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayedHistory that = (PlayedHistory) o;

        if (id != that.id) return false;
        if (contentGroupId != that.contentGroupId) return false;
        if (subscriberId != null ? !subscriberId.equals(that.subscriberId) : that.subscriberId != null) return false;
        if (fileType != null ? !fileType.equals(that.fileType) : that.fileType != null) return false;
        if (playType != null ? !playType.equals(that.playType) : that.playType != null) return false;
        if (offSet != null ? !offSet.equals(that.offSet) : that.offSet != null) return false;
        if (currentPlayDateTime != null ? !currentPlayDateTime.equals(that.currentPlayDateTime) : that.currentPlayDateTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (subscriberId != null ? subscriberId.hashCode() : 0);
        result = 31 * result + (int) (contentGroupId ^ (contentGroupId >>> 32));
        result = 31 * result + (fileType != null ? fileType.hashCode() : 0);
        result = 31 * result + (playType != null ? playType.hashCode() : 0);
        result = 31 * result + (offSet != null ? offSet.hashCode() : 0);
        result = 31 * result + (currentPlayDateTime != null ? currentPlayDateTime.hashCode() : 0);
        return result;
    }
}
