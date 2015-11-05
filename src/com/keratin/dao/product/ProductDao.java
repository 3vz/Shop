package com.keratin.dao.product;

import java.util.List;

import com.keratin.dao.BaseDao;
import com.keratin.model.product.ProductInfo;

public interface ProductDao extends BaseDao<ProductInfo>{
	public List<ProductInfo> findCommend();
	public List<ProductInfo> findClickcount();
	public List<ProductInfo> findSellCount();
}
