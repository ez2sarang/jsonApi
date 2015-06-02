package com.fast2.zimin.navigator.dao;

import com.fast2.zimin.navigator.bean.entity.NavigatorSession;
import com.fast2.zimin.navigator.bean.entity.Subscriber;

/**
 * Created by ez2sarang on 15. 5. 11..
 */
public interface AuthenticationDao {
    Subscriber getSubscriber(String subscriberId, String idType) throws Exception;

    NavigatorSession getSessionByUserId(String userId) throws Exception;

    NavigatorSession getSessionByToken(String sessionToken, String... txToken) throws Exception;

    void saveSession(NavigatorSession session) throws Exception;
}
