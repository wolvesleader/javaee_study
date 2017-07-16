package com.quincy.upload;

import java.io.IOException;
import java.util.Properties;

/**
 * 获取配置的上传文件限制信息
 * @author quincy
 *
 */
public class FileConst {
	
	private static Properties properties = new Properties();
	static{
		try {
			properties.load(ReadUploadFileType.class.getClassLoader().getResourceAsStream("uploadConst.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取配置文件中对上传文件的限制信息
	 * @param key
	 * @return
	 */
	public static String getValue(String key){
		String value = (String)properties.get(key);
		return value.trim();
	}
}
