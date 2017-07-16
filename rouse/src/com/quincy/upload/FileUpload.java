package com.quincy.upload;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.quincy.utils.StringUtil;

/**
 * 文件上传类
 * 该类实现了文件的上传和删除
 * @author quincy
 *
 */

public class FileUpload {
	
	private static String uploadPath = null;//上传路径
	private static String tempPath = null;//文件临时处理目录
	private static File uploadFile = null;//上传的文件
	private static File tempPathFile = null;//临时文件
	private static int sizeThreshold = 1024;//指定使用临时文件处理的界限
	private static int sizeMax = 4194304;//文件最大限制
	
	private static List<FileItem> items = null;
	private static String uploadFileName ;
	
	/**
	 * 当类被加载时进行相关属性的装配
	 */
	static{
		sizeMax = Integer.parseInt(FileConst.getValue("sizeMax"));//读取文件的最大限制
		sizeThreshold = Integer.parseInt(FileConst.getValue("sizeThreshold"));//读取使用临时文件处理的界限
		uploadPath = FileConst.getValue("uploadPath"); //读取上传文件的路径
		uploadFile = new File(uploadPath);//根据上传文件路径创建文件对象
		//如果该文件夹不存在，就创建出来
		if(!uploadFile.exists()){
			uploadFile.mkdirs();
		}
		tempPath = FileConst.getValue("tempPath");//读取临时文件的路径
		tempPathFile = new File(tempPath);//根据临时文件路径创建文件对象
		//如果该文件夹不存在，就创建出来
		if(!tempPathFile.exists()){
			tempPathFile.mkdirs();
		}
	}
	
	
	public static List<FileItem> getFileItems(HttpServletRequest request){
		try {
			if(items == null){
				//创建文件工厂对象
				DiskFileItemFactory factory = new DiskFileItemFactory();
				//设置工厂的相关属性
				factory.setSizeThreshold(sizeThreshold);
				factory.setRepository(tempPathFile);
				//根据工作对象，创建文件上传对象
				ServletFileUpload upload = new ServletFileUpload(factory);
				//设置文件上传对象的编码和最大限制
				upload.setHeaderEncoding("UTF-8");
				upload.setSizeMax(sizeMax);
				items = upload.parseRequest(request);
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return items;
	}
	
	public static String getUploadFileName(){
		return uploadFileName;
	}
	
	
	/**
	 * 上传文件
	 * @param request
	 * @return
	 */
	public static boolean upload(HttpServletRequest request){
		boolean flag = true;
		//判断表单是否多部件表单
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(isMultipart){
			try {
				//根据请求获得FileItem对象集合
				List<FileItem> items = getFileItems(request);
				//判断集合中是否包含合法上传文件
				if(!checkFileType(items)) return false;
				//获得迭代器
				Iterator<FileItem> itr = items.iterator();
				while(itr.hasNext()){
					FileItem item = itr.next();
					//如果文件项是一个表单文件类型，就上传
					if(!item.isFormField()){
						String name = item.getName();
						System.out.println(name);
						if(name != null){
							File fullFile = new File(name);
							uploadFileName = StringUtil.getID() + getSuffix(fullFile.getName());
							File savedFile = new File(uploadPath, uploadFileName);
							item.write(savedFile);
						}
					}
				}
			} catch (FileUploadException e) {
				flag = false;
				e.printStackTrace();
			} catch (Exception e) {
				flag = false;
				e.printStackTrace();
			}
			
		}else{
			flag = false;
			System.out.println("the enctype must be multipart/form-data");
		}
		return flag;
	}
	
	/**
	 * 获取文件后缀名
	 * @param str
	 * @return
	 */
	public static String getSuffix(String str){
		if(str.contains(".")){
			return str.substring(str.lastIndexOf("."));
		}
		return null;
	}
	
	/**
	 * 删除一组文件
	 * @param filePath
	 */
	public static void deleteFile(String[] filePath){
		if(filePath != null && filePath.length > 0){
			for(String path : filePath){
				String realPath = uploadPath + path;
				File delFile = new File(realPath);
				if(delFile.exists()){
					delFile.delete();
				}
			}
		}
	}
	
	/**
	 * 删除单个文件
	 * @param filePath
	 */
	public static void deleteFile(String filePath){
		if(filePath != null && !"".equals(filePath)){
			String[] path = new String[]{filePath};
			deleteFile(path);
		}
	}
	
	/**
	 * 判断上传文件类型
	 * @param items
	 * @return
	 */
	private static Boolean checkFileType(List<FileItem> items){
		Iterator<FileItem> itr = items.iterator();//所有的表单项
		while(itr.hasNext()){
			FileItem item = itr.next();//获得每个表单项
			//判断表单项是不是文件类型
			if(!item.isFormField()){
				String name = item.getName();//获得文件名，包括路径
				if(name != null){
					File fullFile = new File(name);	
					boolean isType = ReadUploadFileType.readUploadFileType(fullFile);
					if(!isType){
						return false;
					}
					break;
				}
			}
		}
		return true;
	}
	
	public static String getPrameter(String name, HttpServletRequest request){
		try {
			List<FileItem> items = getFileItems(request);
			for(FileItem f : items){
				if(f != null && f.isFormField()){
					if(name.equals(f.getFieldName())){
						//System.out.println(new String(f.getString().getBytes("ISO-8859-1"), "utf-8") );
						return f.getString("UTF-8");
					}
				}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
}
