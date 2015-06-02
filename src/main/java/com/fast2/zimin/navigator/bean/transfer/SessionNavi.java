package com.fast2.zimin.navigator.bean.transfer;

import com.fast2.zimin.navigator.bean.SessionInfo;
import com.fast2.zimin.navigator.bean.entity.NavigatorSession;
import com.fast2.zimin.navigator.config.ResultType;

/**
 * Created by ez2sarang on 15. 5. 11..
 */
public class SessionNavi extends ResponseNavi {
    private String sessionToken;

    public SessionNavi(SessionInfo info) {
        super(ResultType.OK);
        this.sessionToken = info.getSessionToken();
        this.setTransactionToken(info.getTransactionToken());
    }

    public SessionNavi(NavigatorSession info) {
        super(ResultType.OK);
        this.sessionToken = info.getSessionTokenId();
        super.setTransactionToken(info.getTxTokenId());
    }

    public SessionNavi(ResultType result) {
        super(result);
    }

    public SessionNavi(String resultCode, String errorString) {
        super(resultCode, errorString);
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }
}
