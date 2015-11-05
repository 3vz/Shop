package com.keratin.dao.order;

import com.keratin.dao.BaseDao;
import com.keratin.model.order.Order;

public interface OrderDao extends BaseDao<Order> {
	public Order findByCustomer(int customerId);
}
