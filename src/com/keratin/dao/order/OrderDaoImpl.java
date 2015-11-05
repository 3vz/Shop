package com.keratin.dao.order;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.keratin.dao.DaoSupport;
import com.keratin.model.order.Order;
@Repository("orderDao")
@Transactional
public class OrderDaoImpl extends DaoSupport<Order> implements OrderDao {

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Order findByCustomer(int customerId) {
		String where = "where customer.id = ?";
		Object[] queryParams = {customerId};
		List<Order> list = find(-1, -1, where, queryParams).getList();
		return list.get(0);
	}

}
