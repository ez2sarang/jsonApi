package com.fast2.zimin.navigator.bean.transfer;

import com.fast2.zimin.navigator.config.ResultType;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by ez2sarang on 15. 3. 25..
 */
public abstract class ResponseNavi {
    private String resultCode;
    private String errorString;
    @JsonIgnore
    private String sessionToken;
    private String transactionToken;

    public ResponseNavi(ResultType result) {
        this.resultCode = result.code;
        this.errorString = result.name();
    }

    public ResponseNavi(ResultType result, String transactionToken) {
        this.resultCode = result.code;
        this.errorString = result.name();
        this.transactionToken = transactionToken;
    }

    public ResponseNavi(String resultCode, String errorString) {
        this.errorString = errorString;
        this.resultCode = resultCode;
    }

    public String getErrorString() {
        return errorString;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultType result) {
        this.resultCode = result.code;
        this.errorString = result.name();
    }

    public void setResultCode(ResultType result, String errorString) {
        this.resultCode = result.code;
        this.errorString = errorString;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getTransactionToken() {
        return transactionToken;
    }

    public void setTransactionToken(String transactionToken) {
        this.transactionToken = transactionToken;
    }
}
