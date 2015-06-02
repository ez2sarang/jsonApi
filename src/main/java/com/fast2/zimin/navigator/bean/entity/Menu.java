package com.fast2.zimin.navigator.bean.entity;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ez2sarang on 15. 4. 9..
 */
@Entity
public class Menu {
    @Id
    private long id;
    private Long parentId;
    private String name;
    private Integer categoryId;
    private Date validTermStart;
    private Date validTermEnd;
    private boolean visible;
    private boolean adult;
    private short showCounts;
    private short showPages;
    private String sortType;
    private byte displayOrder;
    private String userId;
    private Date lastUpdateTime;
    private Date createTime;
    private String menuType;

    @NotFound(action= NotFoundAction.IGNORE)
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "parentId", insertable=false, updatable=false)
    private Menu parent;

    @OneToMany(cascade={CascadeType.REMOVE}, orphanRemoval = true)
    @JoinColumn(name = "parentId", insertable=false, updatable=false)
    @OrderBy("displayOrder, id")
    private List<Menu> children = new LinkedList<Menu>();

    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "categoryId", nullable = true, insertable = true, updatable = true)
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "validTermStart", nullable = true, insertable = true, updatable = true)
    public Date getValidTermStart() {
        return validTermStart;
    }

    public void setValidTermStart(Date validTermStart) {
        this.validTermStart = validTermStart;
    }

    @Basic
    @Column(name = "validTermEnd", nullable = true, insertable = true, updatable = true)
    public Date getValidTermEnd() {
        return validTermEnd;
    }

    public void setValidTermEnd(Date validTermEnd) {
        this.validTermEnd = validTermEnd;
    }

    @Basic
    @Column(name = "visible", nullable = false, insertable = true, updatable = true)
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Basic
    @Column(name = "adult", nullable = false, insertable = true, updatable = true)
    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    @Basic
    @Column(name = "showCounts", nullable = false, insertable = true, updatable = true)
    public short getShowCounts() {
        return showCounts;
    }

    public void setShowCounts(short showCounts) {
        this.showCounts = showCounts;
    }

    @Basic
    @Column(name = "showPages", nullable = false, insertable = true, updatable = true)
    public short getShowPages() {
        return showPages;
    }

    public void setShowPages(short showPages) {
        this.showPages = showPages;
    }

    @Basic
    @Column(name = "sortType", nullable = true, insertable = true, updatable = true, length = 50)
    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    @Basic
    @Column(name = "displayOrder", nullable = false, insertable = true, updatable = true)
    public byte getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(byte displayOrder) {
        this.displayOrder = displayOrder;
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
    @Column(name = "lastUpdateDateTime", nullable = true, insertable = true, updatable = true)
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateDateTime) {
        this.lastUpdateTime = lastUpdateDateTime;
    }

    @Basic
    @Column(name = "createDateTime", nullable = true, insertable = true, updatable = true)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createDateTime) {
        this.createTime = createDateTime;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Menu menu = (Menu) o;

        if (id != menu.id) return false;
        if (visible != menu.visible) return false;
        if (adult != menu.adult) return false;
        if (showCounts != menu.showCounts) return false;
        if (showPages != menu.showPages) return false;
        if (displayOrder != menu.displayOrder) return false;
        if (categoryId != null ? !categoryId.equals(menu.categoryId) : menu.categoryId != null) return false;
        if (validTermStart != null ? !validTermStart.equals(menu.validTermStart) : menu.validTermStart != null)
            return false;
        if (validTermEnd != null ? !validTermEnd.equals(menu.validTermEnd) : menu.validTermEnd != null) return false;
        if (sortType != null ? !sortType.equals(menu.sortType) : menu.sortType != null) return false;
        if (userId != null ? !userId.equals(menu.userId) : menu.userId != null) return false;
        if (lastUpdateTime != null ? !lastUpdateTime.equals(menu.lastUpdateTime) : menu.lastUpdateTime != null)
            return false;
        if (createTime != null ? !createTime.equals(menu.createTime) : menu.createTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int)id;
        result = 31 * result + (categoryId != null ? categoryId.hashCode() : 0);
        result = 31 * result + (validTermStart != null ? validTermStart.hashCode() : 0);
        result = 31 * result + (validTermEnd != null ? validTermEnd.hashCode() : 0);
        result = 31 * result + (visible ? 1 : 0);
        result = 31 * result + (adult ? 1 : 0);
        result = 31 * result + (int) showCounts;
        result = 31 * result + (int) showPages;
        result = 31 * result + (sortType != null ? sortType.hashCode() : 0);
        result = 31 * result + (int) displayOrder;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (lastUpdateTime != null ? lastUpdateTime.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{" +
//                "createTime:" + createTime +
                ", id:" + id +
                ", name:'" + getName() + "'" +
                ", parentId:" + (null==parentId?0:parentId) +
                ", categoryId:'" + categoryId + '\'' +
                ", validTermStart:'" + validTermStart + "'" +
                ", validTermEnd:'" + validTermEnd + "'" +
                ", visible:" + visible +
                ", adult:" + adult +
                ", showCounts:" + showCounts +
                ", showPages:" + showPages +
                ", displayOrder:" + displayOrder +
                ", userId:'" + userId + '\'' +
//                ", lastUpdateTime:" + lastUpdateTime +
                '}';
    }
}
