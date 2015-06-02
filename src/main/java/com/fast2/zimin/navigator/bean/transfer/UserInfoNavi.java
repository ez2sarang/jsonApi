package com.fast2.zimin.navigator.bean.transfer;

import com.fast2.zimin.navigator.config.ResultType;

/**
 * Created by ez2sarang on 15. 5. 14..
 */
public class UserInfoNavi extends ResponseNavi {
    private String userId;
    private String userName;
    private String userPhone;
    private String userGrade;

    public UserInfoNavi() {
        super(ResultType.UNKNOWN_ERROR);
    }

    public UserInfoNavi(ResultType result, String transactionToken) {
        super(result, transactionToken);
    }

    public UserInfoNavi(ResultType result, String transactionToken, String userGrade) {
        super(result, transactionToken);
        this.userGrade = userGrade;
    }

    public UserInfoNavi(ResultType result) {
        super(result);
    }

    public UserInfoNavi(String resultCode, String errorString) {
        super(resultCode, errorString);
    }

    public String getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(String userGrade) {
        this.userGrade = userGrade;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
