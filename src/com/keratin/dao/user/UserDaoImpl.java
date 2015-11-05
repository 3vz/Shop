package com.keratin.dao.user;

import com.keratin.dao.DaoSupport;
import com.keratin.model.user.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("userDao")
@Transactional
public class UserDaoImpl extends DaoSupport<User> implements UserDao {

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public User login(String username, String password) {
		if(username != null && password != null){
			//set search condition.
			String where = "where username=? and password=?";
			//set parameters object array.
			Object[] queryParams = {username,password};
			//implement search.
			List<User> list = find(-1, -1, where, queryParams).getList();
			if(list != null && list.size() > 0){
				//return first element in the list.
				return list.get(0);
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public boolean isUnique(String username) {
		List list = getTemplate().find("from User where username = ?", username);
		if(list != null && list.size() > 0){
			return false;
		}
		return true;
	}
	
	
}
