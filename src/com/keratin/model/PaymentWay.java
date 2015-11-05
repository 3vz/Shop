package com.keratin.model;
/**
 * ֧payment method.
 */
public enum PaymentWay {
	NET_BANK{
		@Override
		public String getName() {
			return "debit/credit card";
		}
	},
	PAPAL {
		@Override
		public String getName() {
			return "Paypal";
		}
	};
	public abstract String getName();
}
