package com.fast2.zimin.system.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fast2.zimin.system.dao.LocaleDao;
import com.fast2.zimin.system.entity.Locale;

@Repository("localeDao")
public class LocaleDaoImpl implements LocaleDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Locale getLocale(String localeCode) throws Exception {
		return (Locale) sessionFactory.getCurrentSession().get(Locale.class,
				localeCode);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Locale> getLocaleList() throws Exception {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Locale.class);
		crit.addOrder(Order.asc("displayOrder"));

		return (List<Locale>) crit.list();
	}
}
