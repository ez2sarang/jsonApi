package com.fast2.zimin.navigator.service.impl;

import com.fast2.zimin.navigator.bean.entity.Menu;
import com.fast2.zimin.navigator.bean.entity.NavigatorSession;
import com.fast2.zimin.navigator.bean.entity.Subscriber;
import com.fast2.zimin.navigator.bean.transfer.*;
import com.fast2.zimin.navigator.config.ResultType;
import com.fast2.zimin.navigator.controller.AuthenticationController;
import com.fast2.zimin.navigator.dao.AuthenticationDao;
import com.fast2.zimin.navigator.dao.PresentationDao;
import com.fast2.zimin.navigator.service.AuthenticationService;
import com.fast2.zimin.util.SimpleRandomKeyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Date;
import java.util.UUID;

/**
 * Created by ez2sarang on 15. 4. 8..
 */
@Service("authenticationService")
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private AuthenticationDao authenticationDao;

    @Autowired
    private PresentationDao presentationDao;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private static final SimpleRandomKeyFactory srf = new SimpleRandomKeyFactory();

    private final int SESSION_TIME_OUT;

    @Inject
    public AuthenticationServiceImpl(@Value("${session.timeout.term}")String term) throws ScriptException {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        this.SESSION_TIME_OUT = ((Double) engine.eval(term)).intValue();
    }

    @Override
    public Subscriber getSubscriber(String subscriberId, String idType) throws Exception {
        try {
            return authenticationDao.getSubscriber(subscriberId, idType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public SessionNavi login(String subscriberId, String idType, String password, String userLocale) throws Exception {
        SessionNavi navi = new SessionNavi(ResultType.UNKNOWN_ERROR);
        Subscriber result = authenticationDao.getSubscriber(subscriberId, idType);
        if (null == result || !"Y".equals(result.getUseYn())) {
            navi.setResultCode(ResultType.INVALID_USER);
        } else {
            if(passwordEncoder.matches(password, result.getPassword())) {
                Menu menu = presentationDao.getRoootMenuId(result.getMenuGroup());
                NavigatorSession session = authenticationDao.getSessionByUserId(subscriberId);
                if(null == session) {
                    session = new NavigatorSession(subscriberId, UUID.randomUUID().toString(), srf.createSessionKey(), new Date(), result.getMenuGroup(), userLocale, result.getUserName(), result.getUserPhone(), result.getUserGrade());
                    if(null != menu) {
                        session.setRootMenuId(menu.getId());
                        session.setRootMenuEndTerm(menu.getValidTermEnd());
                    } else {
                        session.setRootMenuId(-1l);
                        session.setRootMenuEndTerm(null);
                    }
                    authenticationDao.saveSession(session);
                } else {
                    session.setMenuGroup(result.getMenuGroup());
                    session.setSessionTokenId(UUID.randomUUID().toString());
                    session.setTxTokenId(srf.createSessionKey());
                    session.setLastUpdateDateTime(new Date());
                    session.setLocale(userLocale);
                    if(null != menu) {
                        session.setRootMenuId(menu.getId());
                        session.setRootMenuEndTerm(menu.getValidTermEnd());
                    } else {
                        session.setRootMenuId(-1l);
                        session.setRootMenuEndTerm(null);
                    }
                }
                navi = new SessionNavi(session);
            } else {
                navi.setResultCode(ResultType.INVALID_PASSWORD);
            }
        }
        return navi;
    }

    @Override
    public void validSession(ResponseNavi navi) throws Exception {
        NavigatorSession session = authenticationDao.getSessionByToken(navi.getSessionToken());
        if(null == session) {
            navi.setResultCode(ResultType.SESSION_OUT);
        } else {
            if(session.getTxTokenId().equals(navi.getTransactionToken()) && new Date(System.currentTimeMillis()-SESSION_TIME_OUT).before(session.getLastUpdateDateTime())) {
                session.setTxTokenId(srf.createSessionKey());
                session.setLastUpdateDateTime(new Date());
                navi.setResultCode(ResultType.OK);
                navi.setTransactionToken(session.getTxTokenId());
                if(navi instanceof UserInfoNavi) {
                    ((UserInfoNavi) navi).setUserId(session.getUserId());
                    ((UserInfoNavi) navi).setUserName(session.getUserName());
                    ((UserInfoNavi) navi).setUserPhone(session.getUserPhone());
                    ((UserInfoNavi) navi).setUserGrade(session.getUserGrade());
                } else if(navi instanceof MenuListNavi) {
                    if (((MenuListNavi) navi).getRootMenuId() == null) {
                        if(session.getRootMenuEndTerm() != null && new Date().before(session.getRootMenuEndTerm())) {
                            ((MenuListNavi) navi).setRootMenuId(session.getRootMenuId());
                        } else {
                            Menu menu = presentationDao.getRoootMenuId(session.getMenuGroup());
                            if(null != menu) {
                                session.setRootMenuId(menu.getId());
                                session.setRootMenuEndTerm(menu.getValidTermEnd());
                                ((MenuListNavi) navi).setRootMenuId(menu.getId());
                            } else {
                                session.setRootMenuId(-1l);
                                session.setRootMenuEndTerm(null);
                                ((MenuListNavi) navi).setRootMenuId(-1l);
                            }
                        }
                    }
                } else if(navi instanceof UserNavi) {
                    ((UserNavi) navi).setUserId(session.getUserId());
                }
            } else {
                navi.setResultCode(ResultType.SESSION_OUT);
            }
        }
    }

    @Override
    public ResultType releaseSession(String sessionToken, String transactionToken) throws Exception {
        NavigatorSession session = authenticationDao.getSessionByToken(sessionToken);
        if(null == session) {
            return ResultType.SESSION_OUT;
        } else {
            if(session.getTxTokenId().equals(transactionToken) && new Date(System.currentTimeMillis()-SESSION_TIME_OUT).before(session.getLastUpdateDateTime())) {
                session.setTxTokenId("logout");
                return ResultType.OK;
            } else {
                return ResultType.SESSION_OUT;
            }
        }
    }

    @Override
    public boolean checkPassword(String sessionToken, String password) throws Exception {
        NavigatorSession session = authenticationDao.getSessionByToken(sessionToken);
        if(null == session) {
            return false;
        } else {
            Subscriber result = authenticationDao.getSubscriber(session.getUserId(), AuthenticationController.SUBSCRIBER_TYPE_ID);
            if (null == result || !"Y".equals(result.getUseYn())) {
                return false;
            } else {
                return passwordEncoder.matches(password, result.getPassword());
            }
        }
    }
}
