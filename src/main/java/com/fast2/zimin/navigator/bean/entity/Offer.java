package com.fast2.zimin.navigator.bean.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ez2sarang on 15. 4. 9..
 */
@Entity
@Table(name = "Offer")
public class Offer {
    @Id
    private long offerId;
    private String offerType;
    private Long promotionalContentGroupRef;
    private String title;
    private Integer price;
    private String rentalPeriod;
    private Date startDateTime;
    private Date endDateTime;
    private Date licenseStartDateTime;
    private Date licenseEndDateTime;
    private String termsTarget;

    @OneToOne(optional = true)/*(cascade={javax.persistence.CascadeType.ALL}, orphanRemoval = true)*/
//    @JoinTable(name = "ContentGroup", joinColumns = @JoinColumn(name = "promotionalContentGroupRef"), inverseJoinColumns = @JoinColumn(name = "contentGroupId"))
    @JoinColumn(name = "promotionalContentGroupRef", referencedColumnName = "contentGroupId", insertable=false, updatable=false)
    private ContentGroup pcg;

//    @Id
//    @Column(name = "offerId", nullable = false, insertable = true, updatable = true)
    public long getOfferId() {
        return offerId;
    }

    public void setOfferId(long offerId) {
        this.offerId = offerId;
    }

//    @Basic
//    @Column(name = "offerType", nullable = true, insertable = true, updatable = true, length = 100)
    public String getOfferType() {
        return offerType;
    }

    public void setOfferType(String offerType) {
        this.offerType = offerType;
    }

//    @Basic
//    @Column(name = "promotionalContentGroupRef", nullable = true, insertable = true, updatable = true)
    public Long getPromotionalContentGroupRef() {
        return promotionalContentGroupRef;
    }

    public void setPromotionalContentGroupRef(Long promotionalContentGroupRef) {
        this.promotionalContentGroupRef = promotionalContentGroupRef;
    }

//    @Basic
//    @Column(name = "title", nullable = true, insertable = true, updatable = true, length = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    @Basic
//    @Column(name = "price", nullable = true, insertable = true, updatable = true)
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

//    @Basic
//    @Column(name = "rentalPeriod", nullable = true, insertable = true, updatable = true, length = 20)
    public String getRentalPeriod() {
        return rentalPeriod;
    }

    public void setRentalPeriod(String rentalPeriod) {
        this.rentalPeriod = rentalPeriod;
    }

//    @Basic
//    @Column(name = "startDateTime", nullable = true, insertable = true, updatable = true)
    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

//    @Basic
//    @Column(name = "endDateTime", nullable = true, insertable = true, updatable = true)
    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

//    @Basic
//    @Column(name = "licenseStartDateTime", nullable = true, insertable = true, updatable = true)
    public Date getLicenseStartDateTime() {
        return licenseStartDateTime;
    }

    public void setLicenseStartDateTime(Date licenseStartDateTime) {
        this.licenseStartDateTime = licenseStartDateTime;
    }

//    @Basic
//    @Column(name = "licenseEndDateTime", nullable = true, insertable = true, updatable = true)
    public Date getLicenseEndDateTime() {
        return licenseEndDateTime;
    }

    public void setLicenseEndDateTime(Date licenseEndDateTime) {
        this.licenseEndDateTime = licenseEndDateTime;
    }

//    @Basic
//    @Column(name = "termsTarget", nullable = true, insertable = true, updatable = true, length = 20)
    public String getTermsTarget() {
        return termsTarget;
    }

    public void setTermsTarget(String termsTarget) {
        this.termsTarget = termsTarget;
    }

    public ContentGroup getPcg() {
        return pcg;
    }

    public void setPcg(ContentGroup pcg) {
        this.pcg = pcg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Offer offer = (Offer) o;

        if (offerId != offer.offerId) return false;
        if (offerType != null ? !offerType.equals(offer.offerType) : offer.offerType != null) return false;
        if (promotionalContentGroupRef != null ? !promotionalContentGroupRef.equals(offer.promotionalContentGroupRef) : offer.promotionalContentGroupRef != null)
            return false;
        if (title != null ? !title.equals(offer.title) : offer.title != null) return false;
        if (price != null ? !price.equals(offer.price) : offer.price != null) return false;
        if (rentalPeriod != null ? !rentalPeriod.equals(offer.rentalPeriod) : offer.rentalPeriod != null) return false;
        if (startDateTime != null ? !startDateTime.equals(offer.startDateTime) : offer.startDateTime != null)
            return false;
        if (endDateTime != null ? !endDateTime.equals(offer.endDateTime) : offer.endDateTime != null) return false;
        if (licenseStartDateTime != null ? !licenseStartDateTime.equals(offer.licenseStartDateTime) : offer.licenseStartDateTime != null)
            return false;
        if (licenseEndDateTime != null ? !licenseEndDateTime.equals(offer.licenseEndDateTime) : offer.licenseEndDateTime != null)
            return false;
        if (termsTarget != null ? !termsTarget.equals(offer.termsTarget) : offer.termsTarget != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (offerId ^ (offerId >>> 32));
        result = 31 * result + (offerType != null ? offerType.hashCode() : 0);
        result = 31 * result + (promotionalContentGroupRef != null ? promotionalContentGroupRef.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (rentalPeriod != null ? rentalPeriod.hashCode() : 0);
        result = 31 * result + (startDateTime != null ? startDateTime.hashCode() : 0);
        result = 31 * result + (endDateTime != null ? endDateTime.hashCode() : 0);
        result = 31 * result + (licenseStartDateTime != null ? licenseStartDateTime.hashCode() : 0);
        result = 31 * result + (licenseEndDateTime != null ? licenseEndDateTime.hashCode() : 0);
        result = 31 * result + (termsTarget != null ? termsTarget.hashCode() : 0);
        return result;
    }
}
