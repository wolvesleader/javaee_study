package com.quincy.rouse.util;

public class AbandonedStringUtil {
	public static String getFirstCharUppercase(String dest){
		char[] charArray = dest.toCharArray();
		charArray[0] = Character.toUpperCase(charArray[0]);
		return new String(charArray);
	}
	
	/**
	 * 字段的数据类型
	 * 
	 * @param str
	 * @return
	 */
	public static Class<?> typeClass(String dest) {
		if (dest.equals("java.lang.String")) {
			return java.lang.String.class;
		} else if (dest.equals("java.lang.Integer")) {
			return java.lang.Integer.class;
		} else if (dest.equals("java.lang.Character")) {
			return java.lang.Character.class;
		} else if (dest.equals("java.lang.Double")) {
			return java.lang.Double.class;
		} else if (dest.equals("java.lang.Short")) {
			return java.lang.Short.class;
		} else if (dest.equals("java.lang.Byte")) {
			return java.lang.Byte.class;
		} else if (dest.equals("java.lang.Float")) {
			return java.lang.Float.class;
		} else if (dest.equals("java.lang.Boolean")) {
			return java.lang.Boolean.class;
		} else if (dest.equals("java.util.Date")) {
			return java.util.Date.class;
		}
		return null;
	}

}
