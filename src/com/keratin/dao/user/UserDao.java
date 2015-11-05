package com.keratin.dao.user;

import com.keratin.dao.BaseDao;
import com.keratin.model.user.User;

public interface UserDao extends BaseDao<User> {
	public User login(String username, String password);
	public boolean isUnique(String username);
}
