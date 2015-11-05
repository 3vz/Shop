<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加订单</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/common/head.jsp"%>
<div id="box">
	<div id="wdzh_left">
      <div id="left_list"><s:a action="cart_list" namespace="/product">My Cart</s:a></div>
	  <div id="left_list"><s:a action="order_findByCustomer" namespace="/product">My Order</s:a></div>
    </div>
     <s:form action="order_confirm" namespace="/product">
    <div id="wdzh_right">
      <div id="right_tiao">　&gt;　My Order</div>
      <div id="biaodan01">
     
        <table width="688" height="35" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="116" align="right" valign="middle">Receiver：</td>
            <td width="572" align="left" valign="middle">
            <s:textfield name="name" cssClass="bian" size="40"></s:textfield>
            </td>
          </tr>
        </table>
      </div>
	  <div id="biaodan02">
	    <table width="688" height="70" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="116" align="right" valign="middle">Shipping Address：</td>
            <td width="572" align="left" valign="middle">
            <s:textarea name="address" cssClass="bian" cols="50" rows="3"></s:textarea>
            </td>
          </tr>
        </table>
	  </div>
	  <div id="biaodan03">
	    <table width="688" height="35" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="116" align="right" valign="middle">Phone：</td>
            <td width="572" align="left" valign="middle">
            <s:textfield name="mobile" cssClass="bian" size="40"></s:textfield>
            </td>
          </tr>
        </table>
	  </div>
	  <div id="biaodan04">
	    <table width="688" height="140" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="116" height="35" align="right" valign="middle">Payment：</td>
            <td width="572" align="left" valign="middle">
            <p>
				<input type="radio" name="paymentWay" value="网上银行">Debit/Credit Card
			</p>
			<p>
				<input type="radio" name="paymentWay" value="支付宝">Paypal
			</p>
			</td>
          </tr>
        </table>
	  </div>
	  <div id="biaodan05">
	    <table width="688" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="688" align="center">
            <s:submit type="image" src="%{context_path}/css/images/zh01_03.gif"></s:submit>
           </td>
          </tr>
        </table>
        
	  </div>
    </div>
 </s:form>
  <div id="foot"></div>
</div>
</body>
</html>