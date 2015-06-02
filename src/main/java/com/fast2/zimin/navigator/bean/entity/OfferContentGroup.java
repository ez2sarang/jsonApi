package com.fast2.zimin.navigator.bean.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * Created by ez2sarang on 15. 4. 9..
 */
@Entity
@IdClass(OfferContentGroupPK.class)
public class OfferContentGroup {
    private long offerId;
    private long contentGroupId;

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

        OfferContentGroup that = (OfferContentGroup) o;

        if (offerId != that.offerId) return false;
        if (contentGroupId != that.contentGroupId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (offerId ^ (offerId >>> 32));
        result = 31 * result + (int) (contentGroupId ^ (contentGroupId >>> 32));
        return result;
    }
}
