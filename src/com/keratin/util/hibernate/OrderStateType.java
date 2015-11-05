package com.keratin.util.hibernate;

import com.keratin.model.OrderState;

public class OrderStateType extends EnumType<OrderState> {
	public OrderStateType() {
		super(OrderState.class);
	}
}
