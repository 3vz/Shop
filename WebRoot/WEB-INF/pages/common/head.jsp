<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set var="context_path"
	value="#request.get('javax.servlet.forward.context_path')"></s:set>
<link href="${context_path}/css/style_index.css" rel="stylesheet"
	type="text/css" />
<style>
<!--
td {
	font-size: 12px;
}
-->
</style>
<div id="box">
<div id="dark"><s:a action="cart_list" namespace="/product">
	<img src="${context_path}/css/images/index_03.gif" width="28"
		height="14" /> My Cart</s:a> | <s:a action="order_findByCustomer"
	namespace="/product">My Order</s:a>　
<s:if test="#session.customer != null">
	欢迎 　<s:property value="#session.customer.username"/>　
	<s:a action="customer_logout" namespace="/customer">Exit</s:a>
</s:if>
</div>
<div id="logo">
<s:form action="product_findByName" method="post" namespace="/product">
<div id="sou">
	<s:textfield name="name"></s:textfield><br>
	<div style="margin-top: 5px;">
		<b>Popular：</b>
		<s:a action="product_getByCategoryId" namespace="/product">
			<s:param name="category.id" value="'330'"></s:param>
			软件
		</s:a>
	</div>
</div>
<div id="sou_zi">
	<s:submit type="image" src="%{context_path}/css/images/index_09.gif" value="search"></s:submit>
</div>
</s:form>
<div id="sou_zi01">Advanced Search<br />

使用帮助</div>
</div>
<div id="menu">
	<s:a action="index" namespace="/">
<img src="${context_path}/css/images/"
	width="92" height="33" /></s:a>
	<s:a action="product_findNewProduct" namespace="/product"><img
	src="${context_path}/css/images/" width="100" height="33" /></s:a>
	</div>
</div>

