package com.fast2.zimin.navigator.bean.transfer;

import com.fast2.zimin.navigator.config.ResultType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * Created by ez2sarang on 15. 5. 18..
 */
public class ViewedListNavi extends PageableNavi {
    @JsonIgnore
    private String fromDate;
    @JsonIgnore
    private String toDate;
    private List<ViewedInfo> viewedList;

    public ViewedListNavi() {
        super(ResultType.UNKNOWN_ERROR);
    }

    public ViewedListNavi(String resultCode, String errorString) {
        super(resultCode, errorString);
    }

    public ViewedListNavi(ResultType result) {
        super(result);
    }

    public ViewedListNavi(ResultType result, String transactionToken) {
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

    public List<ViewedInfo> getViewedList() {
        return viewedList;
    }

    public void setViewedList(List<ViewedInfo> viewedList) {
        this.viewedList = viewedList;
    }
}
