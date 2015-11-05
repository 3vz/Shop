package com.keratin.model;

import java.util.ArrayList;
import java.util.List;

/**
 * order status.
 */
public enum OrderState {
	DELIVERED {
		@Override
		public String getName() {
			return "shipped";
		}
	},
	FINISH{
		@Override
		public String getName() {
			return "order completed";
		}
	},
	CANCLE{
		@Override
		public String getName() {
			return "cancelled";
		}
	};
	public abstract String getName();
	public static List<String> getValues(){
		List<String> list = new ArrayList<String>();
		for (OrderState orderState : OrderState.values()) {
			list.add(orderState.getName());
		}
		return list;
	}
}
