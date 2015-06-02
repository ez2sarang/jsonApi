package com.fast2.zimin.navigator.bean.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by ez2sarang on 15. 4. 9..
 */
@Entity
public class InOutHistory {
    private long id;
    private boolean logType;
    private boolean valid;
    private String sessionToken;
    private Timestamp createDateTime;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "logType", nullable = false, insertable = true, updatable = true)
    public boolean isLogType() {
        return logType;
    }

    public void setLogType(boolean logType) {
        this.logType = logType;
    }

    @Basic
    @Column(name = "valid", nullable = false, insertable = true, updatable = true)
    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Basic
    @Column(name = "sessionToken", nullable = false, insertable = true, updatable = true, length = 100)
    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    @Basic
    @Column(name = "createDateTime", nullable = false, insertable = true, updatable = true)
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

        InOutHistory that = (InOutHistory) o;

        if (id != that.id) return false;
        if (logType != that.logType) return false;
        if (valid != that.valid) return false;
        if (sessionToken != null ? !sessionToken.equals(that.sessionToken) : that.sessionToken != null) return false;
        if (createDateTime != null ? !createDateTime.equals(that.createDateTime) : that.createDateTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (logType ? 1 : 0);
        result = 31 * result + (valid ? 1 : 0);
        result = 31 * result + (sessionToken != null ? sessionToken.hashCode() : 0);
        result = 31 * result + (createDateTime != null ? createDateTime.hashCode() : 0);
        return result;
    }
}
