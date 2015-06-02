package com.fast2.zimin.navigator.bean.entity;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ez2sarang on 15. 4. 9..
 */
@Entity
public class PlayableContentGroup implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private long rentalId;
    private String subscriberId;
    private long offerId;
    private long contentGroupId;
    private String fileType;
    private Date licenseEndDateTime;
    @Formula("(select o.title from Offer o where o.offerId = offerId)")
    private String offerTitle;
    @Formula("(select o.createDateTime from RentalOffer o where o.id = rentalId)")
    private Date createDateTime;
    @Formula("(select o.price from RentalOffer o where o.id = rentalId)")
    private Integer price;

    public PlayableContentGroup() {
    }

    public PlayableContentGroup(long rentalId, String subscriberId, long offerId, long contentGroupId, String fileType, Date licenseEndDateTime) {
        this.contentGroupId = contentGroupId;
        this.fileType = fileType;
        this.licenseEndDateTime = licenseEndDateTime;
        this.offerId = offerId;
        this.rentalId = rentalId;
        this.subscriberId = subscriberId;
    }

    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "rentalId", nullable = false, insertable = true, updatable = true)
    public long getRentalId() {
        return rentalId;
    }

    public void setRentalId(long rentalId) {
        this.rentalId = rentalId;
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
    @Column(name = "offerId", nullable = false, insertable = true, updatable = true)
    public long getOfferId() {
        return offerId;
    }

    public void setOfferId(long offerId) {
        this.offerId = offerId;
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
    @Column(name = "fileType", nullable = true, insertable = true, updatable = true, length = 10)
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Basic
    @Column(name = "licenseEndDateTime", nullable = false, insertable = true, updatable = true)
    public Date getLicenseEndDateTime() {
        return licenseEndDateTime;
    }

    public void setLicenseEndDateTime(Date licenseEndDateTime) {
        this.licenseEndDateTime = licenseEndDateTime;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public String getOfferTitle() {
        return offerTitle;
    }

    public void setOfferTitle(String offerTitle) {
        this.offerTitle = offerTitle;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayableContentGroup that = (PlayableContentGroup) o;

        if (id != that.id) return false;
        if (rentalId != that.rentalId) return false;
        if (offerId != that.offerId) return false;
        if (contentGroupId != that.contentGroupId) return false;
        if (subscriberId != null ? !subscriberId.equals(that.subscriberId) : that.subscriberId != null) return false;
        if (fileType != null ? !fileType.equals(that.fileType) : that.fileType != null) return false;
        if (licenseEndDateTime != null ? !licenseEndDateTime.equals(that.licenseEndDateTime) : that.licenseEndDateTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (rentalId ^ (rentalId >>> 32));
        result = 31 * result + (subscriberId != null ? subscriberId.hashCode() : 0);
        result = 31 * result + (int) (offerId ^ (offerId >>> 32));
        result = 31 * result + (int) (contentGroupId ^ (contentGroupId >>> 32));
        result = 31 * result + (fileType != null ? fileType.hashCode() : 0);
        result = 31 * result + (licenseEndDateTime != null ? licenseEndDateTime.hashCode() : 0);
        return result;
    }
}
