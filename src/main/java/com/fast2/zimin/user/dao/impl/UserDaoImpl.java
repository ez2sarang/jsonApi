package com.fast2.zimin.user.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fast2.zimin.user.dao.UserDao;
import com.fast2.zimin.user.entity.Role;
import com.fast2.zimin.user.entity.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User getUser(String userId) throws Exception {
		return (User) sessionFactory.getCurrentSession()
				.get(User.class, userId);
	}

	@Override
	public void editUser(User user) throws Exception {
		sessionFactory.getCurrentSession().update(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserList(User user) throws Exception {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				User.class);
		if (StringUtils.isNotBlank(user.getUserName())) {
			crit.add(Restrictions.eq("userName", user.getUserName()));
		}

		if (StringUtils.isNotBlank(user.getUserId())) {
			crit.add(Restrictions.eq("userId", user.getUserId()));
		}

		return (List<User>) crit.list();
	}

	@Override
	public void addUser(User user) throws Exception {
		sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public Role getRole(String roleId) throws Exception {
		return (Role) sessionFactory.getCurrentSession()
				.get(Role.class, roleId);
	}
}
