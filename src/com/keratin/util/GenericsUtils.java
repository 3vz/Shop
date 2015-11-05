package com.keratin.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericsUtils {

	@SuppressWarnings("unchecked")
	public static Class getGenericType(Class clazz){
		Type genType = clazz.getGenericSuperclass();//�õ����͸���  
		Type[] types = ((ParameterizedType) genType).getActualTypeArguments();
		if (!(types[0] instanceof Class)) {
            return Object.class;   
        } 
		return (Class) types[0];
	}

	@SuppressWarnings("unchecked")
	public static String getGenericName(Class clazz){
		return clazz.getSimpleName();
	}
}
