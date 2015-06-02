package com.fast2.zimin.navigator.bean.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ez2sarang on 15. 4. 9..
 */
@Entity
//@IdClass(RentalContentGroupPK.class)
public class RentalContentGroup implements Serializable {
    private Long rentalId;
    private long offerId;
    private long contentGroupId;

    public RentalContentGroup() {
    }

    public RentalContentGroup(long contentGroupId, long offerId, Long rentalId) {
        this.contentGroupId = contentGroupId;
        this.offerId = offerId;
        this.rentalId = rentalId;
    }

    @Id
    @Column(name = "rentalId", nullable = true, insertable = true, updatable = true)
    public Long getRentalId() {
        return rentalId;
    }

    public void setRentalId(Long rentalId) {
        this.rentalId = rentalId;
    }

    @Id
    @Column(name = "offerId", nullable = false, insertable = true, updatable = true)
    public long getOfferId() {
        return offerId;
    }

    public void setOfferId(long offerId) {
        this.offerId = offerId;
    }

    @Id
    @Column(name = "contentGroupId", nullable = false, insertable = true, updatable = true)
    public long getContentGroupId() {
        return contentGroupId;
    }

    public void setContentGroupId(long contentGroupId) {
        this.contentGroupId = contentGroupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RentalContentGroup that = (RentalContentGroup) o;

        if (offerId != that.offerId) return false;
        if (contentGroupId != that.contentGroupId) return false;
        if (rentalId != null ? !rentalId.equals(that.rentalId) : that.rentalId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rentalId != null ? rentalId.hashCode() : 0;
        result = 31 * result + (int) (offerId ^ (offerId >>> 32));
        result = 31 * result + (int) (contentGroupId ^ (contentGroupId >>> 32));
        return result;
    }
}
