package com.quincy.rouse.util;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * rouse工具类，解析xml文件
 * @author quincy
 *javaBean对应多个xml文件主要是为了解析的时候速度比较快，xml文件太大的情况回造成解析速度比较慢
 */
public class RouseParseXml {
	/**
	 * 获取文档对象
	 * @param filePath
	 * @return
	 */
	//需要创建一个小缓存
	
	private static  Document getDocument(String filePath){
		try {
			SAXReader reader = new SAXReader();
			return reader.read(new File(filePath));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}
	 @SuppressWarnings("unchecked")
	public static void parse(String filePath){
		 Document document = getDocument(filePath);
		 Element rootEle = document.getRootElement();
		 Element firstEle = rootEle.element("class");
		List<Attribute> firstAttributes = firstEle.attributes();
		 for (Attribute attribute : firstAttributes) {
			System.out.println(attribute.getValue());
		}
		 System.out.println();
		 List<Element> eles = firstEle.elements();
		 for (Element element : eles) {
			List<Attribute> attributes = element.attributes();
			for (Attribute attribute : attributes) {
				//System.out.println(attribute.getName());
				System.out.println(attribute.getValue());
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		URL resource = RouseParseXml.class.getResource("/mapping/rouse-user.xml");
		parse(resource.getPath());
		
		
	}
	
}
