package com.fast2.zimin.navigator.bean.transfer;

import com.fast2.zimin.navigator.config.ResultType;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by ez2sarang on 15. 5. 18..
 */
public class CheckViewAuthorityNavi {
    @JsonIgnore
    private Long cgId;
    @JsonIgnore
    private String assetType;
    @JsonIgnore
    private String clientId;
    @JsonIgnore
    private String fileName;
    private String resultCode;
    private String errorString;
    private boolean hasAuth;
    public CheckViewAuthorityNavi() {
        this(ResultType.UNKNOWN_ERROR);
    }
    public CheckViewAuthorityNavi(ResultType result) {
        this.resultCode = result.code;
        this.errorString = result.name();
    }

    public CheckViewAuthorityNavi(String resultCode, String errorString) {
        this.errorString = errorString;
        this.resultCode = resultCode;
    }

    public void setResultCode(ResultType result, String errorString) {
        this.resultCode = result.code;
        this.errorString = errorString;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public Long getCgId() {
        return cgId;
    }

    public void setCgId(Long cgId) {
        this.cgId = cgId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getErrorString() {
        return errorString;
    }

    public void setErrorString(String errorString) {
        this.errorString = errorString;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isHasAuth() {
        return hasAuth;
    }

    public void setHasAuth(boolean hasAuth) {
        this.hasAuth = hasAuth;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }
}
