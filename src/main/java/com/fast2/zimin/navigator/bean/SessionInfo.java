package com.fast2.zimin.navigator.bean;

import java.util.UUID;

/**
 * Created by ez2sarang on 15. 5. 12..
 */
public class SessionInfo {
    private String userId;
    private String userLocale;
    private String sessionToken;
    private String transactionToken;
    private String menuGroup;

    public SessionInfo(String userId, String userLocale, String sessionToken, String transactionToken, String menuGroup) {
        this.sessionToken = sessionToken;
        this.transactionToken = transactionToken;
        this.userId = userId;
        this.userLocale = userLocale;
        this.menuGroup = menuGroup;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserLocale() {
        return userLocale;
    }

    public void setUserLocale(String userLocale) {
        this.userLocale = userLocale;
    }

    public String getMenuGroup() {
        return menuGroup;
    }

    public void setMenuGroup(String menuGroup) {
        this.menuGroup = menuGroup;
    }

    public boolean equalsToken(String sessionToken, String transactionToken) {
        if(this.sessionToken.equals(sessionToken) && this.transactionToken.equals(transactionToken)) {
            this.transactionToken = UUID.randomUUID().toString();
            return true;
        }
        return false;
    }
}
