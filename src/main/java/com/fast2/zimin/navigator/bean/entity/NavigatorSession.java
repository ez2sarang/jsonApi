package com.fast2.zimin.navigator.bean.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by ez2sarang on 15. 4. 9..
 */
@Entity
public class NavigatorSession {
    private long id;
    private String userId;
    private String sessionTokenId;
    private String txTokenId;
    private Date lastUpdateDateTime;
    private String menuGroup;
    private Long rootMenuId;
    private Date rootMenuEndTerm;
    private String locale;
    private String userName;
    private String userPhone;
    private String userGrade;

    public NavigatorSession() {
    }

    public NavigatorSession(String userId, String sessionTokenId, String txTokenId, Date lastUpdateDateTime, String menuGroup, String locale, String userName, String userPhone, String userGrade) {
        this.userId = userId;
        this.sessionTokenId = sessionTokenId;
        this.txTokenId = txTokenId;
        this.lastUpdateDateTime = lastUpdateDateTime;
        this.menuGroup = menuGroup;
        this.locale = locale;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userGrade = userGrade;
    }

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userId", nullable = false, insertable = true, updatable = true, length = 50)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "sessionTokenId", nullable = false, insertable = true, updatable = true, length = 100)
    public String getSessionTokenId() {
        return sessionTokenId;
    }

    public void setSessionTokenId(String sessionTokenId) {
        this.sessionTokenId = sessionTokenId;
    }

    @Basic
    @Column(name = "lastUpdateDateTime", nullable = false, insertable = true, updatable = true)
    public Date getLastUpdateDateTime() {
        return lastUpdateDateTime;
    }

    public void setLastUpdateDateTime(Date lastUpdateDateTime) {
        this.lastUpdateDateTime = lastUpdateDateTime;
    }

    @Basic
    @Column(name = "txTokenId", nullable = false, insertable = true, updatable = true)
    public String getTxTokenId() {
        return txTokenId;
    }

    public void setTxTokenId(String txTokenId) {
        this.txTokenId = txTokenId;
    }

    @Basic
    @Column(name = "menuGroup", nullable = true, insertable = true, updatable = true, length = 16)
    public String getMenuGroup() {
        return menuGroup;
    }

    public void setMenuGroup(String menuGroup) {
        this.menuGroup = menuGroup;
    }

    @Basic
    @Column(name = "locale", nullable = true, insertable = true, updatable = true, length = 10)
    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(String userGrade) {
        this.userGrade = userGrade;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Long getRootMenuId() {
        return rootMenuId;
    }

    public void setRootMenuId(Long rootMenuId) {
        this.rootMenuId = rootMenuId;
    }

    public Date getRootMenuEndTerm() {
        return rootMenuEndTerm;
    }

    public void setRootMenuEndTerm(Date rootMenuEndTerm) {
        this.rootMenuEndTerm = rootMenuEndTerm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NavigatorSession that = (NavigatorSession) o;

        if (id != that.id) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (sessionTokenId != null ? !sessionTokenId.equals(that.sessionTokenId) : that.sessionTokenId != null)
            return false;
        if (lastUpdateDateTime != null ? !lastUpdateDateTime.equals(that.lastUpdateDateTime) : that.lastUpdateDateTime != null)
            return false;
        if (txTokenId != null ? !txTokenId.equals(that.txTokenId) : that.txTokenId != null) return false;
        if (menuGroup != null ? !menuGroup.equals(that.menuGroup) : that.menuGroup != null) return false;
        if (locale != null ? !locale.equals(that.locale) : that.locale != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (sessionTokenId != null ? sessionTokenId.hashCode() : 0);
        result = 31 * result + (lastUpdateDateTime != null ? lastUpdateDateTime.hashCode() : 0);
        result = 31 * result + (txTokenId != null ? txTokenId.hashCode() : 0);
        result = 31 * result + (menuGroup != null ? menuGroup.hashCode() : 0);
        result = 31 * result + (locale != null ? locale.hashCode() : 0);
        return result;
    }
}
