package com.keratin.model.product;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;
/**
 * product class.
 */
public class ProductCategory implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private int level = 1;
	private Set<ProductCategory> children;
	private ProductCategory parent;
	private Set<ProductInfo> products = new TreeSet<ProductInfo>();
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<ProductCategory> getChildren() {
		return children;
	}
	public void setChildren(Set<ProductCategory> children) {
		this.children = children;
	}
	public ProductCategory getParent() {
		return parent;
	}
	public void setParent(ProductCategory parent) {
		this.parent = parent;
	}
	public Set<ProductInfo> getProducts() {
		return products;
	}
	public void setProducts(Set<ProductInfo> products) {
		this.products = products;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
}
