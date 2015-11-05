<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/common/common_admin.jsp" %>
<!-- 类别 -->
<s:url action="category_add" namespace="/admin/product"
	var="category_add"></s:url>
<s:url action="category_list" namespace="/admin/product"
	var="category_list"></s:url>
<!-- 商品 -->
<s:url action="product_add" namespace="/admin/product" var="product_add"></s:url>
<s:url action="product_list" namespace="/admin/product"
	var="product_list"></s:url>
<!-- 订单 -->
<s:url action="order_list" namespace="/admin/product" var="order_list"></s:url>
<s:url action="order_query" namespace="/admin/product" var="order_query"></s:url>
<div id="left">
<div id="left_top"></div>
<div id="left_mid">
<div id="category_title">Type Manage</div>
<div id="category_sub">
	<a target="abc" href="${category_add}">Add Type</a>
</div>
<div id="category_sub">
	<a target="abc" href="${category_list}">All Types</a>
</div>
<div id="category_title">Product Manage</div>
<div id="category_sub">
	<a target="abc" href="${product_add}">Add Product</a>
</div>
<div id="category_sub">
	<a target="abc" href="${product_list}">Check ALl Products</a>
</div>
<div id="category_title">Order Manage</div>
<div id="category_sub">
	<a target="abc" href="${order_list}">Check Order</a>
</div>
<div id="category_sub">
	<a target="abc" href="${order_query}">Search Order</a>
</div>
</div>
</div>