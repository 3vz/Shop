package com.keratin.dao.user;

import com.keratin.dao.BaseDao;
import com.keratin.model.user.Customer;

public interface CustomerDao extends BaseDao<Customer> {
	public Customer login(String username, String password);
	public boolean isUnique(String username);
}
