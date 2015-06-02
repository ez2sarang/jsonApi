package com.fast2.zimin.user.service;

import java.util.List;

import com.fast2.zimin.user.entity.Role;
import com.fast2.zimin.user.entity.User;

public interface UserService {

	public User getUser(String userId) throws Exception;

	public void editUser(User user) throws Exception;

	public List<User> getUserList(User user) throws Exception;

	public void addUser(User user) throws Exception;

	public Role getRole(String roleId) throws Exception;
}
