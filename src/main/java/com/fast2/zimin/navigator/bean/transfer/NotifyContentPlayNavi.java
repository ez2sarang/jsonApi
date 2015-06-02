package com.fast2.zimin.navigator.bean.transfer;

import com.fast2.zimin.navigator.config.ResultType;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by ez2sarang on 15. 5. 18..
 */
public class NotifyContentPlayNavi extends UserNavi {
    @JsonIgnore
    private Long cgId;
    @JsonIgnore
    private String assetType;
    @JsonIgnore
    private String controlType;
    @JsonIgnore
    private double offset;
    public NotifyContentPlayNavi() {
        super(ResultType.UNKNOWN_ERROR);
    }

    public NotifyContentPlayNavi(String resultCode, String errorString) {
        super(resultCode, errorString);
    }

    public NotifyContentPlayNavi(ResultType result) {
        super(result);
    }

    public NotifyContentPlayNavi(ResultType result, String transactionToken) {
        super(result, transactionToken);
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

    public String getControlType() {
        return controlType;
    }

    public void setControlType(String controlType) {
        this.controlType = controlType;
    }

    public double getOffset() {
        return offset;
    }

    public void setOffset(double offset) {
        this.offset = offset;
    }
}
