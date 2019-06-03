package com.greenvillage.controller;

import com.greenvillage.pojo.FeeScale;
import com.greenvillage.pojo.Result;
import com.greenvillage.service.FeeScaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author:quincy
 * Date:2019-03-18
 */
@RestController
@RequestMapping("/fee")
public class FeeScaleController {

    @Autowired
    private FeeScaleService feeScaleService;

    @RequestMapping("/scale")
    public Result<List<FeeScale>> findAllPrintPoint(){

        List<FeeScale> allFeeScale = feeScaleService.findAllFeeScale();
        Result<List<FeeScale>> result = new Result<>();
        result.setData(allFeeScale);
        return result;
    }

    @RequestMapping("/findById")
    public Result<FeeScale> findById(String id){
        FeeScale feeScale = feeScaleService.findById(id);
        Result<FeeScale> result = new Result<>();
        result.setData(feeScale);
        return result;
    }
    @RequestMapping("/updateById")
    public Result<String> updateById(FeeScale feeScale){
        int line = feeScaleService.updateById(feeScale);
        Result<String> result = new Result<>();
        if (line != 1){
            result.setStatusCode("500");
            result.setMessage("修改失败");
        }
        return result;
    }

    @RequestMapping("/deleteById")
    public Result<String> deleteById(String id){
        feeScaleService.deleteById(id);
        return new Result<String>();
    }
}
