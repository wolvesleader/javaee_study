package com.greenvillage.mapper;


import com.greenvillage.pojo.User;
import com.greenvillage.pojo.WorkList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {


    public User findByPwdAndName(@Param("username") String username,@Param("password") String password);

    List<WorkList> findWorkList(Long id);

    List<User> findAllUser();

    User findById(String id);

    int updateById(User user);

    void deleteById(String id);

    void save(User user);
}