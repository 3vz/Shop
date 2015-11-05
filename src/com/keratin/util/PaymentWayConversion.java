package com.keratin.util;

import com.keratin.model.PaymentWay;
import org.apache.struts2.util.StrutsTypeConverter;

import java.util.Map;

public class PaymentWayConversion extends StrutsTypeConverter{

	@SuppressWarnings("unchecked")
	@Override
	public Object convertFromString(Map arg0, String[] arg1, Class arg2) {
		PaymentWay[] paymentWays = PaymentWay.values();
		for (PaymentWay paymentWay : paymentWays) {
			if(paymentWay.getName().equals(arg1[0])){
				return paymentWay;
			}
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	@Override
	public String convertToString(Map arg0, Object arg1) {
		PaymentWay paymentWay = (PaymentWay)arg1;
		return paymentWay.getName();
	}

}
