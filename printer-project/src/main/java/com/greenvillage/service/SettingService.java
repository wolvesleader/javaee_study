package com.greenvillage.service;

import com.greenvillage.pojo.Setting;

import java.util.List;

/**
 * author:quincy
 * Date:2019-03-18
 */
public interface SettingService {

     List<Setting> findAllSetting();

    Setting findById(String id);

    int updateById(Setting setting);

    void deleteById(String id);

    void save(Setting setting);
}
