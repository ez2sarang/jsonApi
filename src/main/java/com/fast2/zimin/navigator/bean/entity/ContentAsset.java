package com.fast2.zimin.navigator.bean.entity;

import javax.persistence.*;

/**
 * Created by ez2sarang on 15. 4. 8..
 */
@Entity
@IdClass(ContentAssetPK.class)
public class ContentAsset {

    private long contentAssetId;
    private long contentGroupId;
    private String contentSubsetType;
    private String fileType;
    private String fileName;

    @Basic
    @Column(name = "contentAssetId", nullable = false, insertable = true, updatable = true)
    public long getContentAssetId() {
        return contentAssetId;
    }

    public void setContentAssetId(long contentAssetId) {
        this.contentAssetId = contentAssetId;
    }

    @Id
    @Column(name = "contentGroupId", nullable = false, insertable = true, updatable = true)
    public long getContentGroupId() {
        return contentGroupId;
    }

    public void setContentGroupId(long contentGroupId) {
        this.contentGroupId = contentGroupId;
    }

    @Id
    @Column(name = "contentSubsetType", nullable = false, insertable = true, updatable = true, length = 3)
    public String getContentSubsetType() {
        return contentSubsetType;
    }

    public void setContentSubsetType(String contentSubsetType) {
        this.contentSubsetType = contentSubsetType;
    }

    @Id
    @Column(name = "fileType", nullable = false, insertable = true, updatable = true, length = 10)
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Basic
    @Column(name = "fileName", nullable = true, insertable = true, updatable = true, length = 256)
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContentAsset that = (ContentAsset) o;

        if (contentAssetId != that.contentAssetId) return false;
        if (contentGroupId != that.contentGroupId) return false;
        if (contentSubsetType != null ? !contentSubsetType.equals(that.contentSubsetType) : that.contentSubsetType != null)
            return false;
        if (fileType != null ? !fileType.equals(that.fileType) : that.fileType != null) return false;
        if (fileName != null ? !fileName.equals(that.fileName) : that.fileName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (contentAssetId ^ (contentAssetId >>> 32));
        result = 31 * result + (int) (contentGroupId ^ (contentGroupId >>> 32));
        result = 31 * result + (contentSubsetType != null ? contentSubsetType.hashCode() : 0);
        result = 31 * result + (fileType != null ? fileType.hashCode() : 0);
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        return result;
    }
}
