package com.keratin.dao.product;

import org.springframework.stereotype.Repository;

import com.keratin.dao.DaoSupport;
import com.keratin.model.product.ProductCategory;
@Repository("productCategoryDao")
public class ProductCategoryDaoImpl extends DaoSupport<ProductCategory> implements ProductCategoryDao {

}
