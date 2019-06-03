package com.greenvillage.controller;

import com.greenvillage.pojo.PrintRecord;
import com.greenvillage.pojo.Result;
import com.greenvillage.service.PrintRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author:quincy
 * Date:2019-03-18
 */
@RestController
@RequestMapping("/printrecord")
public class PrintRecordController {

    @Autowired
    private PrintRecordService printRecordService;

    @RequestMapping("/findAllPrintRecord")
    public Result<List<PrintRecord>> findAllPrintRecord(){

        List<PrintRecord> allPrintRecord = printRecordService.findAllPrintRecord();
        Result<List<PrintRecord>> result = new Result<>();
        result.setData(allPrintRecord);
        return result;
    }

    @RequestMapping("/deleteById")
    public Result<String> deleteById(String id){
        printRecordService.deleteById(id);
        return new Result<String>();
    }
}
