package com.fast2.zimin.navigator.bean.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ez2sarang on 15. 4. 9..
 */
@Entity
@IdClass(MenuOfferMapPK.class)
public class MenuOfferMap {
    @Id
    private long menuId;
    private long offerId;
    private boolean priority;
    private byte displayOrder;
    private String userId;
    private Timestamp createDateTime;

//    @Id
//    @Column(name = "menuId", nullable = false, insertable = true, updatable = true)
    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

//    @Id
//    @Column(name = "offerId", nullable = false, insertable = true, updatable = true)
    public long getOfferId() {
        return offerId;
    }

    public void setOfferId(long offerId) {
        this.offerId = offerId;
    }

//    @Basic
//    @Column(name = "priority", nullable = false, insertable = true, updatable = true)
    public boolean isPriority() {
        return priority;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
    }

//    @Basic
//    @Column(name = "displayOrder", nullable = false, insertable = true, updatable = true)
    public byte getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(byte displayOrder) {
        this.displayOrder = displayOrder;
    }

//    @Basic
//    @Column(name = "userId", nullable = true, insertable = true, updatable = true, length = 50)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

//    @Basic
//    @Column(name = "createDateTime", nullable = true, insertable = true, updatable = true)
    public Timestamp getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Timestamp createDateTime) {
        this.createDateTime = createDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuOfferMap that = (MenuOfferMap) o;

        if (menuId != that.menuId) return false;
        if (offerId != that.offerId) return false;
        if (priority != that.priority) return false;
        if (displayOrder != that.displayOrder) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (createDateTime != null ? !createDateTime.equals(that.createDateTime) : that.createDateTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int)menuId;
        result = 31 * result + (int) (offerId ^ (offerId >>> 32));
        result = 31 * result + (priority ? 1 : 0);
        result = 31 * result + (int) displayOrder;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (createDateTime != null ? createDateTime.hashCode() : 0);
        return result;
    }
}
