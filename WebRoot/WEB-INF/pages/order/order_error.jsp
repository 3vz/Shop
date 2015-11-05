<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Order</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/common/head.jsp"%>
<div id="box">
<div id="wdzh_left">
      <div id="left_list"><s:a action="cart_list" namespace="/product">My Cart</s:a></div>
	  <div id="left_list"><s:a action="order_findByCustomer" namespace="/product">My Order</s:a></div>
    </div>
    <div id="wdzh_right">
      <div id="right_tiao">　&gt;　My Order</div>
      <div id="biaodan" align="center">
      	<br>
        <h3>Sorry, no shopping information！</h3>
      </div>
    </div>
 <s:url var="first">
	<s:param name="pageNo" value="1"></s:param>
</s:url>
<s:url var="previous">
	<s:param name="pageNo" value="pageModel.pageNo-1"></s:param>
</s:url>
<s:url var="last">
	<s:param name="pageNo" value="pageModel.bottomPageNo"></s:param>
</s:url>
<s:url var="next">
	<s:param name="pageNo" value="pageModel.pageNo+1"></s:param>
</s:url>
<s:include value="/WEB-INF/pages/common/page.jsp"></s:include>
  <div id="foot"></div>
</div>
</body>
</html>