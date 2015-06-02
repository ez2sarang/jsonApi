package com.fast2.zimin.navigator.bean.transfer;

import com.fast2.zimin.navigator.config.ResultType;

import java.util.List;

/**
 * Created by ez2sarang on 15. 5. 15..
 */
public class CGInfoNavi extends ResponseNavi {
    private String offerTitle;
    private List<CGInfo> cgList;

    public CGInfoNavi() {
        super(ResultType.UNKNOWN_ERROR);
    }

    public CGInfoNavi(ResultType result) {
        super(result);
    }

    public CGInfoNavi(String resultCode, String errorString) {
        super(resultCode, errorString);
    }

    public CGInfoNavi(ResultType result, String transactionToken) {
        super(result, transactionToken);
    }

    public List<CGInfo> getCgList() {
        return cgList;
    }

    public void setCgList(List<CGInfo> cgList) {
        this.cgList = cgList;
    }

    public String getOfferTitle() {
        return offerTitle;
    }

    public void setOfferTitle(String offerTitle) {
        this.offerTitle = offerTitle;
    }
}
