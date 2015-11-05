package com.keratin.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
/**
 * String util class.
 */
public class StringUitl {
	public static Random random = new Random();

	public static String getStringTime(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
		return sdf.format(date);
	}

	public static String createOrderId(){
		StringBuffer sb = new StringBuffer();
		sb.append(getStringTime());
		for (int i = 0; i < 3; i++) {
			sb.append(random.nextInt(9));
		}
		return sb.toString();
	}
	/**
	 * check string's validation.
	 * @param s
	 * @return
	 */
	public static boolean validateString(String s){
		if(s != null && s.trim().length() > 0){//����ַ�����Ϊ�շ���true
			return true;
		}
		return false;//�ַ���Ϊ�շ���false
	}
	/**
	 * check float's validation
	 * @param f
	 * @return
	 */
	public static boolean validateFloat(Float f){
		try {
			if(f != null && f > 0){
				return true;
			}
		} catch (Exception e) {}
		return false;
	}
}
