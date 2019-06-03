package com.greenvillage.controller;

import com.greenvillage.pojo.Result;
import com.greenvillage.pojo.Setting;
import com.greenvillage.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author:quincy
 * Date:2019-03-18
 */
@RestController
@RequestMapping("/set")
public class SettingController {

    @Autowired
    private SettingService settingService;

    @RequestMapping("/findAllSetting")
    public Result<List<Setting>> findAllSetting(){

        List<Setting> allSetting = settingService.findAllSetting();
        Result<List<Setting>> result = new Result<>();
        result.setData(allSetting);
        return result;
    }

    @RequestMapping("/findById")
    public Result<Setting> findById(String id){
        Setting setting = settingService.findById(id);
        Result<Setting> result = new Result<>();
        result.setData(setting);
        return result;
    }
    @RequestMapping("/updateById")
    public Result<String> updateById(Setting setting){
        int line = settingService.updateById(setting);
        Result<String> result = new Result<>();
        if (line != 1){
            result.setStatusCode("500");
            result.setMessage("修改失败");
        }
        return result;
    }

    @RequestMapping("/deleteById")
    public Result<String> deleteById(String id){
        settingService.deleteById(id);
        return new Result<String>();
    }

    @RequestMapping("/save")
    public Result<String> save(Setting setting) {
        settingService.save(setting);
        return new Result<>();
    }
}
