package com.greenvillage.utils.docstopdfconverter;

/**
 * author:quincy
 * Date:2019-03-27
 */

import com.aspose.words.Document;
import com.aspose.words.SaveFormat;
import com.greenvillage.utils.stringutils.StringUtil;

import java.io.File;
import java.io.FileOutputStream;

public class Word2Pdf {

    /**
     *
     * @param address 需要转换的路径
     */
    public static void word2pdf(String address) {
        try {
            //替换doc docx
            String suffix = StringUtil.getSington().getSuffix(address);
            File file = new File(address.replace(suffix,"pdf"));
            FileOutputStream os = new FileOutputStream(file);
            Document doc = new Document(address);  //Address是将要被转化的word文档
            doc.save(os, SaveFormat.PDF);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}