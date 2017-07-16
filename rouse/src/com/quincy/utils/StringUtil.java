package com.quincy.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {
	/**
	 * 生成 年月日 + 四位随机数ID
	 * @return String类型ID
	 */
	public static String getID(){
		String ranNum = getDate();
		for(int i = 0; i < 4; i ++){
			int num = (int)(Math.random() * 10);
			ranNum += num;
		}		
		return ranNum;
	}
	
	/**
	 * 获得日期 年月日
	 * @return String类型年月日
	 */
	public static String getDate(){
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss-SS-");
		 return formatter.format(new Date());
	}
}
