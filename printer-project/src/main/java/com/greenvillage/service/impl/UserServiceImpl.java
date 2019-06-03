package com.greenvillage.service.impl;

import com.greenvillage.mapper.UserMapper;
import com.greenvillage.pojo.User;
import com.greenvillage.pojo.WorkList;
import com.greenvillage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author:quincy
 * Date:2019-03-18
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {

        return userMapper.findByPwdAndName(username,password);
    }

    @Override
    public List<WorkList> findWorkList(Long id) {
        return userMapper.findWorkList(id);
    }

    @Override
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }

    @Override
    public User findById(String id) {
        return userMapper.findById(id);
    }

    @Override
    public int updateById(User user) {
        return userMapper.updateById(user);
    }

    @Override
    public void deleteById(String id) {
        userMapper.deleteById(id);
    }

    @Override
    public void save(User user) {
        userMapper.save(user);
    }
}
