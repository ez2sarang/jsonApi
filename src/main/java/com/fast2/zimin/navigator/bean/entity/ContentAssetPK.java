package com.fast2.zimin.navigator.bean.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by ez2sarang on 15. 4. 9..
 */
public class ContentAssetPK implements Serializable {
    private long contentGroupId;
    private String contentSubsetType;
    private String fileType;

    @Column(name = "contentGroupId", nullable = false, insertable = true, updatable = true)
    @Id
    public long getContentGroupId() {
        return contentGroupId;
    }

    public void setContentGroupId(long contentGroupId) {
        this.contentGroupId = contentGroupId;
    }

    @Column(name = "contentSubsetType", nullable = false, insertable = true, updatable = true, length = 3)
    @Id
    public String getContentSubsetType() {
        return contentSubsetType;
    }

    public void setContentSubsetType(String contentSubsetType) {
        this.contentSubsetType = contentSubsetType;
    }

    @Column(name = "fileType", nullable = false, insertable = true, updatable = true, length = 10)
    @Id
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContentAssetPK that = (ContentAssetPK) o;

        if (contentGroupId != that.contentGroupId) return false;
        if (contentSubsetType != null ? !contentSubsetType.equals(that.contentSubsetType) : that.contentSubsetType != null)
            return false;
        if (fileType != null ? !fileType.equals(that.fileType) : that.fileType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (contentGroupId ^ (contentGroupId >>> 32));
        result = 31 * result + (contentSubsetType != null ? contentSubsetType.hashCode() : 0);
        result = 31 * result + (fileType != null ? fileType.hashCode() : 0);
        return result;
    }
}
