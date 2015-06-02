package com.fast2.zimin.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fast2.zimin.user.dao.UserDao;
import com.fast2.zimin.user.entity.Role;
import com.fast2.zimin.user.entity.User;
import com.fast2.zimin.user.service.UserService;

@Service("userService")
@Transactional(propagation = Propagation.SUPPORTS)
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public User getUser(String userId) throws Exception {
		return userDao.getUser(userId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void editUser(User user) throws Exception {
		userDao.editUser(user);
	}

	@Override
	public List<User> getUserList(User user) throws Exception {
		return userDao.getUserList(user);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addUser(User user) throws Exception {
		userDao.addUser(user);
	}

	@Override
	public Role getRole(String roleId) throws Exception {
		return userDao.getRole(roleId);
	}
}
