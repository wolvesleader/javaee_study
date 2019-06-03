package com.greenvillage.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * author:quincy
 * Date:2019-03-25
 */
@RestController
@RequestMapping("/custom")
public class CustomFileUploadController {


    @RequestMapping("/upload")
    public String upload(@RequestParam("files") MultipartFile[] files) {
        //String path = "D:\\1abc\\upload\\";
        String path = "/Users/quincy/Desktop/test/upload/";
        StringBuffer stringBuffer = new StringBuffer();
        //判断file数组不能为空并且长度大于0
        if (files != null && files.length > 0) {
            //循环获取file数组中得文件
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                //保存文件
                saveFile(file, path,stringBuffer);
            }
        }
        String url = stringBuffer.toString();

        return url.charAt(url.length() - 1) == ',' ? url.substring(0,url.length() - 1) : url;
    }
    private boolean saveFile ( MultipartFile file, String path,StringBuffer stringBuffer){

        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                File filepath = new File(path);
                if (!filepath.exists())
                    filepath.mkdirs();
                // 文件保存路径
                String savePath = path + file.getOriginalFilename();
                // 转存文件
                file.transferTo(new File(savePath));
                stringBuffer.append(savePath + ",");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}