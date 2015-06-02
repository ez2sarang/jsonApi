package com.fast2.zimin.navigator.bean.transfer;

import com.fast2.zimin.navigator.config.ResultType;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by ez2sarang on 15. 3. 26..
 */
public class PageableNavi extends UserNavi {
    private Integer listCount;
    private int totalCount;
    private int pageNo;
    @JsonIgnore
    private String sortItem;
    @JsonIgnore
    private String sortDirection;

    public PageableNavi(String resultCode, String errorString) {
        super(resultCode, errorString);
    }

    public PageableNavi(ResultType result) {
        super(result);
    }

    public PageableNavi(ResultType result, String transactionToken) {
        super(result, transactionToken);
    }

    public Integer getListCount() {
        return listCount;
    }

    public void setListCount(Integer listCount) {
        this.listCount = listCount;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    public String getSortItem() {
        return sortItem;
    }

    public void setSortItem(String sortItem) {
        this.sortItem = sortItem;
    }
}
