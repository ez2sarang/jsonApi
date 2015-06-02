package com.fast2.zimin.navigator.bean.transfer;

import com.fast2.zimin.navigator.config.ResultType;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by ez2sarang on 15. 5. 18..
 */
public class AssetTypeNavi extends ResponseNavi {
    @JsonIgnore
    private Long offerId;
    @JsonIgnore
    private Long cgId;
    private String assetType;

    public AssetTypeNavi() {
        super(ResultType.UNKNOWN_ERROR);
    }

    public AssetTypeNavi(ResultType result, String transactionToken) {
        super(result, transactionToken);
    }

    public AssetTypeNavi(String resultCode, String errorString) {
        super(resultCode, errorString);
    }

    public AssetTypeNavi(ResultType result) {
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

}
