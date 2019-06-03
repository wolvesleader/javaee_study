package com.greenvillage.utils.stringutils;

public class StringUtil {

    private StringUtil(){}

    private static StringUtil stringUtil = new StringUtil();

    public static StringUtil getSington(){
        return stringUtil;
    }


    public String getSuffix(String url){
        int lastPoint = url.lastIndexOf(".");
        //获取到文件的后缀名
        String substring = url.substring(lastPoint + 1, url.length());
        return substring;
    }

    public boolean isContains(String url){
        if (!url.contains("doc") && !url.contains("docx")){
            return false;
        }
        String suffix = getSuffix(url);
        if ("doc".equals(suffix) || "docx".equals(suffix)){
            return true;
        }
        return false;
    }

}
