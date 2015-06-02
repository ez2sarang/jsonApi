package com.fast2.zimin.navigator.bean.entity;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ez2sarang on 15. 4. 9..
 */
@Entity
public class RentalOffer implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private long offerId;
    private String subscriberId;
    private Date createDateTime;
    private Date licenseEndDateTime;
    private String fileType;
    private String menuId;
    private int price;
    @Formula("(select o.title from Offer o where o.offerId = offerId)")
    private String offerTitle;


    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    @Column(name = "subscriberId", nullable = false, insertable = true, updatable = true, length = 32)
    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    @Basic
    @Column(name = "createDateTime", nullable = false, insertable = true, updatable = true)
    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    @Basic
    @Column(name = "licenseEndDateTime", nullable = false, insertable = true, updatable = true)
    public Date getLicenseEndDateTime() {
        return licenseEndDateTime;
    }

    public void setLicenseEndDateTime(Date licenseEndDateTime) {
        this.licenseEndDateTime = licenseEndDateTime;
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
    @Column(name = "menuId", nullable = false, insertable = true, updatable = true, length = 50)
    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getOfferTitle() {
        return offerTitle;
    }

    public void setOfferTitle(String offerTitle) {
        this.offerTitle = offerTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RentalOffer that = (RentalOffer) o;

        if (id != that.id) return false;
        if (offerId != that.offerId) return false;
        if (subscriberId != null ? !subscriberId.equals(that.subscriberId) : that.subscriberId != null) return false;
        if (createDateTime != null ? !createDateTime.equals(that.createDateTime) : that.createDateTime != null)
            return false;
        if (licenseEndDateTime != null ? !licenseEndDateTime.equals(that.licenseEndDateTime) : that.licenseEndDateTime != null)
            return false;
        if (fileType != null ? !fileType.equals(that.fileType) : that.fileType != null) return false;
        if (menuId != null ? !menuId.equals(that.menuId) : that.menuId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (offerId ^ (offerId >>> 32));
        result = 31 * result + (subscriberId != null ? subscriberId.hashCode() : 0);
        result = 31 * result + (createDateTime != null ? createDateTime.hashCode() : 0);
        result = 31 * result + (licenseEndDateTime != null ? licenseEndDateTime.hashCode() : 0);
        result = 31 * result + (fileType != null ? fileType.hashCode() : 0);
        result = 31 * result + (menuId != null ? menuId.hashCode() : 0);
        return result;
    }
}
