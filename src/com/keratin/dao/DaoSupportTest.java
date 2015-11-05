package com.keratin.dao;

import com.keratin.model.PageModel;
import com.keratin.model.order.Order;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
/**
 * test class
 */
public class DaoSupportTest {
	private DaoSupport<Order> daoSupport;
	@Before
	public void setUp() throws Exception {
		daoSupport = new DaoSupport<Order>();
	}
	@After
	public void tearDown() throws Exception {
		daoSupport=null;
	}
	@Test
	public void testFindIntInt() {
		Map<String, String> orderby = new HashMap<String, String>(1);
		orderby.put("createTime", "desc");
		PageModel<Order> pageModel = daoSupport.find(1, 3);
		assertNotNull("cannot get data",pageModel);
	}
}
