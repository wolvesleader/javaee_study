package com.greenvillage.service;

import com.greenvillage.pojo.Setting;
import com.greenvillage.pojo.User;
import com.greenvillage.pojo.WorkList;

import java.util.List;

/**
 * author:quincy
 * Date:2019-03-18
 */

public interface UserService {


    public User login(String username,String password);

    List<WorkList> findWorkList(Long id);

    List<User> findAllUser();

    User findById(String id);

    int updateById(User user);

    void deleteById(String id);

    void save(User user);
}
