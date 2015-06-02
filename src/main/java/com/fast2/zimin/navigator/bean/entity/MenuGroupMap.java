package com.fast2.zimin.navigator.bean.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ez2sarang on 15. 4. 9..
 */
@Entity
@IdClass(MenuGroupMapPK.class)
public class MenuGroupMap {
    private int groupId;
    private int menuId;
    private String userId;
    private Timestamp createDateTime;

    @Id
    @Column(name = "groupId", nullable = false, insertable = true, updatable = true)
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Id
    @Column(name = "menuId", nullable = false, insertable = true, updatable = true)
    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    @Basic
    @Column(name = "userId", nullable = true, insertable = true, updatable = true, length = 50)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "createDateTime", nullable = true, insertable = true, updatable = true)
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

        MenuGroupMap that = (MenuGroupMap) o;

        if (groupId != that.groupId) return false;
        if (menuId != that.menuId) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (createDateTime != null ? !createDateTime.equals(that.createDateTime) : that.createDateTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = groupId;
        result = 31 * result + menuId;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (createDateTime != null ? createDateTime.hashCode() : 0);
        return result;
    }
}
