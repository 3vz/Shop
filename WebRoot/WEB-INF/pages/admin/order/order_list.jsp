<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/common/common_admin.jsp"%>
<div id="right">
<div id="right_top01">
<table width="693" height="29" border="0" class="word01">
	<tr>
		<td width="140" align="center">Order No</td>
		<td width="60" align="center">Price</td>
		<td width="63" align="center">Cosumer</td>
		<td width="70" align="center">Payment</td>
		<td width="140" align="center">Create Time</td>
		<td width="70" align="center">Order Status</td>
		<td width="150" align="center">Update</td>
	</tr>
</table>
</div>
<div id="right_mid">
<div id="tiao">
<table width="693" height="29" border="0">
	<s:iterator value="pageModel.list">
		<tr>
			<td width="140" align="center"><s:property value="orderId" /></td>
			<td width="60" align="center"><s:property value="totalPrice" /></td>
			<td width="63" align="center"><s:property value="customer.username" /></td>
			<td width="70" align="center"><s:property value="paymentWay.getName()" /></td>
			<td width="140" align="center"><s:date name="createTime" format="yyyyyearMMMonthdDay HH:mm" /></td>
			<td width="70" align="center"><s:property value="orderState.getName()" /></td>
			<td width="150" align="center">
				<s:url action="order_select" namespace="/admin/product"
				var="order_select">
				<s:param name="orderId" value="orderId"></s:param>
			</s:url> <input type="button" value="UPdate Order Status"
				onclick="openWindow('${order_select}',350,150);"></td>
		</tr>
	</s:iterator>
</table>
</div>
</div>
<div id="right_foot01"><s:url action="order_list"
	namespace="/admin/product" var="first">
	<s:param name="pageNo" value="1"></s:param>
</s:url> <s:url action="order_list" namespace="/admin/product" var="previous">
	<s:param name="pageNo" value="pageModel.pageNo-1"></s:param>
</s:url> <s:url action="order_list" namespace="/admin/product" var="last">
	<s:param name="pageNo" value="pageModel.bottomPageNo"></s:param>
</s:url> <s:url action="order_list" namespace="/admin/product" var="next">
	<s:param name="pageNo" value="pageModel.pageNo+1"></s:param>
</s:url> <s:include value="/WEB-INF/pages/common/page.jsp"></s:include></div>
</div>