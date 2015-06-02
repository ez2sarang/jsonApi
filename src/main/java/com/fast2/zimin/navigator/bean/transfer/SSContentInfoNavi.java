package com.fast2.zimin.navigator.bean.transfer;

import com.fast2.zimin.navigator.config.ResultType;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by ez2sarang on 15. 5. 18..
 */
public class SSContentInfoNavi extends UserNavi {
    @JsonIgnore
    private Long offerId;
    @JsonIgnore
    private Long cgId;
    @JsonIgnore
    private String assetType;
    private String ssPath;
    private double offSet;

    public SSContentInfoNavi() {
        super(ResultType.UNKNOWN_ERROR);
    }

    public SSContentInfoNavi(String resultCode, String errorString) {
        super(resultCode, errorString);
    }

    public SSContentInfoNavi(ResultType result, String transactionToken) {
        super(result, transactionToken);
    }

    public SSContentInfoNavi(ResultType result) {
        super(result);
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

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public double getOffSet() {
        return offSet;
    }

    public void setOffSet(double offSet) {
        this.offSet = offSet;
    }

    public String getSsPath() {
        return ssPath;
    }

    public void setSsPath(String ssPath) {
        this.ssPath = ssPath;
    }
}
