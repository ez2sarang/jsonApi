package com.fast2.zimin.navigator.bean.transfer;

import com.fast2.zimin.navigator.config.ResultType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * Created by ez2sarang on 15. 5. 18..
 */
public class PurchasedListNavi extends PageableNavi {
    @JsonIgnore
    private String fromDate;
    @JsonIgnore
    private String toDate;
    private List<PurchasedInfo> purchasedList;
    public PurchasedListNavi() {
        super(ResultType.UNKNOWN_ERROR);
    }

    public PurchasedListNavi(String resultCode, String errorString) {
        super(resultCode, errorString);
    }

    public PurchasedListNavi(ResultType result) {
        super(result);
    }

    public PurchasedListNavi(ResultType result, String transactionToken) {
        super(result, transactionToken);
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public List<PurchasedInfo> getPurchasedList() {
        return purchasedList;
    }

    public void setPurchasedList(List<PurchasedInfo> purchasedList) {
        this.purchasedList = purchasedList;
    }
}
