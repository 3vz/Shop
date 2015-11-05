package com.keratin.util;

import com.keratin.model.Gender;
import org.apache.struts2.util.StrutsTypeConverter;

import java.util.Map;

public class GenderConversion extends StrutsTypeConverter {

	@SuppressWarnings("unchecked")
	@Override
	public Object convertFromString(Map arg0, String[] arg1, Class arg2) {
		Gender sexs[] = Gender.values();
		for (Gender sex : sexs) {
			if(sex.getName().equals(arg1[0])){
				return sex;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String convertToString(Map arg0, Object arg1) {
		Gender sex = (Gender) arg1;
		return sex.getName();
	}
}
