package com.fast2.zimin.util.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fast2.zimin.util.dao.UtilityDao;

@Repository
public class UtilityDaoImpl implements UtilityDao {

	@Autowired SessionFactory sessionFactory;

	@Override
	public String getNextCustomTableIdx(String tableName) throws Exception {
		Session currentSession = sessionFactory.getCurrentSession();
		String hql = "SELECT fn_getNextCustomTableIdx(:tableName)";
//		String hql = "SELECT fn_getNextCustomTableIdx('SubscriberId')";
		Query query = currentSession.createSQLQuery(hql);
		query.setString("tableName", tableName);
		return (String)query.uniqueResult();
	}
}
