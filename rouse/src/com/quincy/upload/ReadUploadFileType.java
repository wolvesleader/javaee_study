package com.quincy.upload;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.MimetypesFileTypeMap;
/**
 * 这个类的主要目的就是读取上传文件的类型
 * 并且进行判断是否符合上传要求
 * @author quincy
 *
 */
public class ReadUploadFileType {
	
	private static Properties properties = new Properties();
	static{
		try {
			properties.load(ReadUploadFileType.class.getClassLoader().getResourceAsStream("allow_upload_file_type.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 判断上传文件是否允许的上传类型
	 * 进行了两种信息的比对，一个是扩展名的比对，一个是MIME类型的比对
	 * @param uploadFile 上传的文件
	 * @return 是否验证成功
	 */
	public static Boolean readUploadFileType(File uploadFile){
		if(uploadFile != null && uploadFile.length() > 0){
			//获得上传文件的扩展名
			String ext = uploadFile.getName().substring(uploadFile.getName().lastIndexOf(".") + 1).toLowerCase();
			//获取配置文件中所允许的文件扩展名
			List<String> allowFileType = new ArrayList<String>();
			for(Object key : properties.keySet()){
				String value = (String)properties.get(key);
				String[] values = value.split(",");
				for(String v : values){
					allowFileType.add(v.trim());
				}
			}
			//获取上传文件的文件类型信息
			String uploadFileMimeType = new MimetypesFileTypeMap().getContentType(uploadFile).toLowerCase();
			//如果上传文件的类型包含在允许类型中，并且上传文件的扩展名包含在配置文件允许的扩展名中，则返回true
			//配置文件的key就是扩展名，而value是文件的MIME信息，两者不同，所以两个都要判断，同时满足才能返回true
			return allowFileType.contains(uploadFileMimeType) && properties.keySet().contains(ext);
		}
		return true;
	}
}
