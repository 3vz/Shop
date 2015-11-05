package com.keratin.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Gender class
 */
public enum Gender {
	BOY{
		@Override
		public String getName() {
			return "Male";
		}
	},
	GIRL{
		@Override
		public String getName() {
			return "Female";
		}
	},
	ALL{
		@Override
		public String getName() {
			return "None";
		}
	};
	/**
	 * get value.
	 */
	public abstract String getName();
	public static List<String> getValues(){
		List<String> list = new ArrayList<String>();
		for (Gender sex : Gender.values()) {
			list.add(sex.getName());
		}
		return list;
	}
}
