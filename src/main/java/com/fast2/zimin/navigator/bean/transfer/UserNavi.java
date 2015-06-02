package com.fast2.zimin.navigator.bean.transfer;

import com.fast2.zimin.navigator.config.ResultType;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by ez2sarang on 15. 5. 18..
 */
public class UserNavi extends ResponseNavi {
    @JsonIgnore
    private String userId;

    public UserNavi(String resultCode, String errorString) {
        super(resultCode, errorString);
    }

    public UserNavi(ResultType result) {
        super(result);
    }

    public UserNavi(ResultType result, String transactionToken) {
        super(result, transactionToken);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
