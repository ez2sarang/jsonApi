package com.fast2.zimin.navigator.bean.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by ez2sarang on 15. 4. 9..
 */
public class MenuGroupMapPK implements Serializable {
    private int groupId;
    private int menuId;

    @Column(name = "groupId", nullable = false, insertable = true, updatable = true)
    @Id
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Column(name = "menuId", nullable = false, insertable = true, updatable = true)
    @Id
    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuGroupMapPK that = (MenuGroupMapPK) o;

        if (groupId != that.groupId) return false;
        if (menuId != that.menuId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = groupId;
        result = 31 * result + menuId;
        return result;
    }
}
