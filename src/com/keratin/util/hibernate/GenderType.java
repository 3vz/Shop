package com.keratin.util.hibernate;

import com.keratin.model.Gender;

public class GenderType extends EnumType<Gender> {
	public GenderType() {
		super(Gender.class);
	}
}
