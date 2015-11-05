package com.keratin.dao.product;

import com.keratin.dao.DaoSupport;
import com.keratin.model.PageModel;
import com.keratin.model.product.ProductInfo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository("productDao")
@Transactional
public class ProductDaoImpl extends DaoSupport<ProductInfo> implements ProductDao {

	/**
	 * check top 10 products.
	 */
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<ProductInfo> findClickcount() {
		Map<String, String> orderby = new HashMap<String, String>();
		orderby.put("clickcount", "desc");
		PageModel<ProductInfo> pageModel = find(1, 10, orderby);
		return pageModel.getList();
	}
	/**
	 * check top 10 products, and order in upload time.
	 */
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<ProductInfo> findCommend() {
		String where = "where commend=?";
		Object[] parames = {true};
		Map<String, String> orderby = new HashMap<String, String>();
		orderby.put("createTime", "desc");
		PageModel<ProductInfo> pageModel = find(where,parames ,orderby,1,10);
		return pageModel.getList();
	}
	/**
	 * check most popular top 10 prodcuts.
	 */
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<ProductInfo> findSellCount() {
		Map<String, String> orderby = new HashMap<String, String>();
		orderby.put("sellCount", "desc");
		PageModel<ProductInfo> pageModel = find(1, 10, orderby);
		return pageModel.getList();
	}
}
