package com.greenvillage.mapper;

import com.greenvillage.pojo.Setting;

import java.util.List;

/**
 * author:quincy
 * Date:2019-03-18
 */
public interface SettingMapper {

    public List<Setting> findAllSetting();

    Setting findById(String id);

    int updateById(Setting setting);

    void deleteById(String id);

    void save(Setting setting);
}
