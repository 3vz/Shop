package com.keratin.util.hibernate;

import com.keratin.model.PaymentWay;

public class PaymentWayType extends EnumType<PaymentWay> {
	public PaymentWayType() {
		super(PaymentWay.class);
	}
}
