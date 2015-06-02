package com.fast2.zimin.navigator.controller;

import com.fast2.zimin.navigator.bean.transfer.ResponseNavi;
import com.fast2.zimin.navigator.bean.transfer.SessionNavi;
import com.fast2.zimin.navigator.bean.transfer.UserInfoNavi;
import com.fast2.zimin.navigator.config.ResultType;
import com.fast2.zimin.navigator.service.AuthenticationService;
import com.fast2.zimin.util.TheLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ez2sarang on 15. 4. 3..
 */
@Controller
public class AuthenticationController {

    public static final String SUBSCRIBER_TYPE_STB = "STB";
    public static final String SUBSCRIBER_TYPE_ID = "ID";

    @Autowired
    private AuthenticationService authenticationService;

//    @Inject
//    private FileSystemResource fsResource;

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public
    @ResponseBody
    ResponseNavi login(HttpServletRequest request) throws Exception {
        String userId = request.getParameter("userId");
        String userPw = request.getParameter("userPw");
        String userLocale = request.getParameter("userLocale");
        try {
            return authenticationService.login(userId, SUBSCRIBER_TYPE_ID, userPw, userLocale);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new SessionNavi(ResultType.UNKNOWN_ERROR);
    }

    @RequestMapping(value = "/getUserInfo")
    public @ResponseBody
    ResponseNavi getUserInfo(@ModelAttribute("navi") UserInfoNavi navi) {
        try {
            authenticationService.validSession(navi);
            if(navi.getResultCode() == ResultType.OK.code) {
                return navi;
            } else {
                return new UserInfoNavi(ResultType.SESSION_OUT);
            }
        } catch (Exception e) {
            TheLogger.error(e, "Exeption[%s]", e.getMessage());
            return new UserInfoNavi(ResultType.UNKNOWN_ERROR.code, e.getMessage());
        }
    }

    @RequestMapping(value = "/logout")
    public @ResponseBody
    String logout(HttpServletRequest request) {
        try {
            ResultType result = authenticationService.releaseSession(request.getParameter("sessionToken"), request.getParameter("transactionToken"));
            if(result == ResultType.OK) {
                return String.format("{\"resultCode\":\"%s\", \"errorString\":\"\"}", ResultType.OK.code);
            } else {
                return String.format("{\"resultCode\":\"%s\", \"errorString\":\"%s\"}", ResultType.SESSION_OUT.code, ResultType.SESSION_OUT.toString());
            }
        } catch (Exception e) {
            TheLogger.error(e, "Exeption[%s]", e.getMessage());
            return String.format("{\"resultCode\":\"%s\", \"errorString\":\"%s\"}", ResultType.UNKNOWN_ERROR.code, e.getMessage());
        }
    }
}
