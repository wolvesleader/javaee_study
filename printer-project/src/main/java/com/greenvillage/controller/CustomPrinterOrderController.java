package com.greenvillage.controller;

import com.greenvillage.pojo.FeeScale;
import com.greenvillage.pojo.PrintOrder;
import com.greenvillage.pojo.Result;
import com.greenvillage.pojo.Student;
import com.greenvillage.service.CustomPrinterOrderService;
import com.greenvillage.service.FeeScaleService;
import com.greenvillage.utils.docstopdfconverter.Word2Pdf;
import com.greenvillage.utils.stringutils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * author:quincy
 * Date:2019-03-25
 */
@RestController
@RequestMapping("/custom")
public class CustomPrinterOrderController {

    @Autowired
    private FeeScaleService feeScaleService;

    @Autowired
    private CustomPrinterOrderService customPrinterOrderService;

    @RequestMapping("/savePrinterOrder")
    public Result<PrintOrder> savePrinterOrder(PrintOrder printOrder){
        //生成pdf文件
        //设置数据
        //返回页面数据准备
        FeeScale feeScale = feeScaleService.findFeeScaleByCondition(printOrder);
        BigDecimal bigDecimal = new BigDecimal(printOrder.getPrinterNumber());
        BigDecimal bigDecimal2 = new BigDecimal(feeScale.getPayMoney());
        printOrder.setTotalOrderMoney(bigDecimal.multiply(bigDecimal2).toString());
        //保存到数据库
        customPrinterOrderService.save(printOrder);
        //保存到打印记录表中

        Result<PrintOrder> result = new Result<>();
        result.setData(printOrder);

        return result;
    }

    @RequestMapping("/findOrderById")
    public Result<PrintOrder> findOrderById(String id){
        PrintOrder printOrder = customPrinterOrderService.findOrderById(id);
        Result<PrintOrder> result = new Result<>();
        result.setData(printOrder);
        return result;
    }

    @RequestMapping("/printFile")
    public Result<Student> printFile(PrintOrder printOrder,HttpServletRequest request){
        Result<Student> result = new Result<>();
        Student student = (Student) request.getSession().getAttribute("loginStudent");
        if (student == null){
            result.setMessage("没有登陆");
            result.setData(student);
            result.setStatusCode("203");
        }else{
            try {
                String accountBalance = student.getAccountBalance();
                double studentMoney = Double.parseDouble(accountBalance);
                String totalOrderMoney = printOrder.getTotalOrderMoney();
                double i = Double.parseDouble(totalOrderMoney);
                if (studentMoney >= i){
                    //开始打印
                    //需要把上传的word文件转换为pdf格式的文件
                    //printOrder
                    String printerUrl = printOrder.getPrinterUrl();
                    //开始转换文件
                    if (StringUtil.getSington().isContains(printerUrl)){
                        Word2Pdf.word2pdf(printerUrl);
                        result.setMessage("成功");
                        result.setData(student);
                        result.setStatusCode("200");
                    }else{
                        //把当前登陆的用户写回去.格式不支持
                        result.setMessage("失败,文件格式不支持");
                        result.setData(student);
                        //不支持该格式文件的打印
                        result.setStatusCode("800");
                    }
                }else{
                    //去充值页面
                    result.setMessage("余额不足");
                    result.setData(student);
                    result.setStatusCode("401");
                }
            }catch (Exception x){
                x.printStackTrace();
            }
        }
        return result;
    }



}
