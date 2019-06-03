package com.greenvillage.controller;

import com.greenvillage.pojo.PrintPoint;
import com.greenvillage.pojo.Result;
import com.greenvillage.service.PrintPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author:quincy
 * Date:2019-03-18
 */
@RestController
@RequestMapping("/printpoint")
public class PrintPointController {

    @Autowired
    private PrintPointService printPointService;

    @RequestMapping("/findAllPrintPoint")
    public Result<List<PrintPoint>> findAllPrintPoint(){
        List<PrintPoint> allPrintPoint = printPointService.findAllPrintPoint();
        Result<List<PrintPoint>> result = new Result<>();
        result.setData(allPrintPoint);
        return result;
    }
    @RequestMapping("/findById")
    public Result<PrintPoint> findById(String id){
        PrintPoint printPoint = printPointService.findById(id);
        Result<PrintPoint> result = new Result<>();
        result.setData(printPoint);
        return result;
    }
    @RequestMapping("/updateById")
    public Result<String> updateById(PrintPoint printPoint){
        int line = printPointService.updateById(printPoint);
        Result<String> result = new Result<>();
        if (line != 1){
            result.setStatusCode("500");
            result.setMessage("修改失败");
        }
        return result;
    }

    @RequestMapping("/deleteById")
    public Result<String> deleteById(String id){
        printPointService.deleteById(id);
        return new Result<String>();
    }
}
