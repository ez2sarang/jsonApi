package com.fast2.zimin.navigator.bean.transfer;

import com.fast2.zimin.navigator.config.ResultType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * Created by ez2sarang on 15. 3. 26..
 */
public class OfferListNavi extends PageableNavi {
    @JsonIgnore
    private Long menuId;
    private List<OfferInfo> offerList;

    public OfferListNavi() {
        super(ResultType.UNKNOWN_ERROR);
    }

    public OfferListNavi(ResultType result) {
        super(result);
    }

    public OfferListNavi(String resultCode, String errorString) {
        super(resultCode, errorString);
    }

    public OfferListNavi(ResultType result, String transactionToken) {
        super(result, transactionToken);
        this.offerList = offerList;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public List<OfferInfo> getOfferList() {
        return offerList;
    }

    public void setOfferList(List<OfferInfo> offerList) {
        this.offerList = offerList;
    }
}
