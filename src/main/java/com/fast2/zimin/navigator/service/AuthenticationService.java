package com.fast2.zimin.navigator.service;

import com.fast2.zimin.navigator.bean.entity.Subscriber;
import com.fast2.zimin.navigator.bean.transfer.ResponseNavi;
import com.fast2.zimin.navigator.bean.transfer.SessionNavi;
import com.fast2.zimin.navigator.config.ResultType;

/**
 * Created by ez2sarang on 15. 5. 11..
 */
public interface AuthenticationService {
    Subscriber getSubscriber(String subscriberId, String idType) throws Exception;

    SessionNavi login(String subscriberId, String idType, String password, String userLocale) throws Exception;

    void validSession(ResponseNavi navi) throws Exception;

    ResultType releaseSession(String sessionToken, String transactionToken) throws Exception;

    boolean checkPassword(String sessionToken, String password) throws Exception;
}
