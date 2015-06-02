package com.fast2.zimin.navigator.bean.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by ez2sarang on 15. 4. 9..
 */
public class MenuOfferMapPK implements Serializable {
    private long menuId;
    private long offerId;

    @Column(name = "menuId", nullable = false, insertable = true, updatable = true)
    @Id
    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    @Column(name = "offerId", nullable = false, insertable = true, updatable = true)
    @Id
    public long getOfferId() {
        return offerId;
    }

    public void setOfferId(long offerId) {
        this.offerId = offerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuOfferMapPK that = (MenuOfferMapPK) o;

        if (menuId != that.menuId) return false;
        if (offerId != that.offerId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int)menuId;
        result = 31 * result + (int) (offerId ^ (offerId >>> 32));
        return result;
    }
}
