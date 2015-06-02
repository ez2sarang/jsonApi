package com.fast2.zimin.navigator.session;

import com.fast2.zimin.navigator.bean.SessionInfo;
import com.fast2.zimin.util.SimpleRandomKeyFactory;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * Created by ez2sarang on 15. 5. 12..
 */
public class SessionHelper {
    public static final String SESSION_INFO_KEY = "SUBSCRIBER";

    public Table<String, String, Object> sessionTable = HashBasedTable.create();

    /**
     *
     * @param sessionToken Row
     * @param userId Column
     * @param value
     */
    public static void sessionRegist(HttpServletRequest request, String userId, String userLocale, String menuGroup, boolean existSession) {
        HttpSession session;
        if(existSession) {
            request.getSession(false).invalidate();
        }
        session = request.getSession(true);
        session.setAttribute(SESSION_INFO_KEY, new SessionInfo(userId, userLocale, session.getId(), UUID.randomUUID().toString(), menuGroup));
        System.out.println("ID:"+session.getId());
        session.setMaxInactiveInterval(1800);

        /*if(sessionTable.containsColumn(userId)) {

        } else {
            sessionTable.put(sessionToken, userId, value);
        }*/
    }

    public static boolean existSession(HttpServletRequest request) {
        if (request.getSession(false) == null || (request.getSession(false).getAttribute(SESSION_INFO_KEY) == null)) {
            return false;
        }
        return true;
        /*Map<String, Object> userMap = sessionTable.row(sessionToken);
        if(null == userMap || userMap.size() == 0) {
            return false;
        } else if(userMap.size() > 1) {
            return false;
        } else {

        }
        return true;*/
    }

    public static boolean validSession(HttpServletRequest request) throws Exception {
        if (existSession(request) && getSessionInfo(request).equalsToken(request.getParameter("sessionToken"), request.getParameter("transactionToken"))) {
            return true;
        }
        return false;
    }

    public static SessionInfo getSessionInfo(HttpServletRequest request) throws Exception {
        return (SessionInfo)request.getSession(false).getAttribute(SESSION_INFO_KEY);
    }

    public void releaseSession(String sessionToken) {

    }
}
