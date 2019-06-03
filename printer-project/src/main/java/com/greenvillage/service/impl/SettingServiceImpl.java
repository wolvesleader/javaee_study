package com.greenvillage.service.impl;

import com.greenvillage.mapper.SettingMapper;
import com.greenvillage.pojo.Setting;
import com.greenvillage.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author:quincy
 * Date:2019-03-18
 */
@Service
public class SettingServiceImpl implements SettingService {



    @Autowired
    private SettingMapper settingMapper;

    @Override
    public List<Setting> findAllSetting() {
        return settingMapper.findAllSetting();
    }

    @Override
    public Setting findById(String id) {
        return settingMapper.findById(id);
    }

    @Override
    public int updateById(Setting setting) {
        return settingMapper.updateById(setting);
    }

    @Override
    public void deleteById(String id) {
        settingMapper.deleteById(id);
    }

    @Override
    public void save(Setting setting) {
        settingMapper.save(setting);
    }
}
