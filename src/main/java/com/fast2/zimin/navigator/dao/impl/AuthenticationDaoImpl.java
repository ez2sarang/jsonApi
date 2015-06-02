package com.fast2.zimin.navigator.dao.impl;

import com.fast2.zimin.navigator.bean.entity.NavigatorSession;
import com.fast2.zimin.navigator.bean.entity.Subscriber;
import com.fast2.zimin.navigator.dao.AuthenticationDao;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by ez2sarang on 15. 4. 8..
 */
@Repository("authenticationDao")
public class AuthenticationDaoImpl implements AuthenticationDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Subscriber getSubscriber(String subscriberId, String idType) throws Exception {
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(Subscriber.class)
                .add(Restrictions.eq("subscriberId", subscriberId))
                .add(Restrictions.eq("idType", idType))
                ;
        return (Subscriber)criteria.uniqueResult();
    }

    @Override
    public NavigatorSession getSessionByUserId(String userId) throws Exception {
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(NavigatorSession.class)
                .add(Restrictions.eq("userId", userId))
                ;
        return (NavigatorSession)criteria.uniqueResult();
    }

    @Override
    public NavigatorSession getSessionByToken(String sessionToken, String... txToken) throws Exception {
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(NavigatorSession.class)
                .add(Restrictions.eq("sessionTokenId", sessionToken))
                ;
        if(null != txToken && txToken.length>0) {
            criteria.add(Restrictions.eq("txTokenId", txToken[0]));
        }
        return (NavigatorSession)criteria.uniqueResult();
    }

    @Override
    public void saveSession(NavigatorSession session) throws Exception {
        sessionFactory.getCurrentSession().save(session);
    }
}
