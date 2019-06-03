package com.greenvillage.controller;

import com.greenvillage.pojo.*;
import com.greenvillage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * author:quincy
 * Date:2019-03-18
 */

@RestController
@RequestMapping("/user")
public class AdminController {

    @Autowired
    private UserService userService;


    @RequestMapping("/login")
    public Result<User> login(User user, HttpServletRequest request){
        Result<User> result = new Result<>();
        HttpSession session = request.getSession();
        User userFromDb = userService.login(user.getUsername(), user.getPassword());
        if (userFromDb != null){
            result.setMessage("成功");
            result.setStatusCode("200");
            result.setData(userFromDb);
            session.setAttribute("loginUser",userFromDb);
        }else {
            result.setMessage("失败");
            result.setStatusCode("404");
            result.setData(userFromDb);
        }
        return result;
    }

    @RequestMapping("/adminLogout")
    public Result<Student> studentLogout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("loginUser");
        Result<Student> result = new Result<>();
        result.setMessage("退出成功");
        return result;
    }

    @RequestMapping("/findWorkList")
    public Result<List<WorkList>> findWorkList(Long id){
        List<WorkList> workList = userService.findWorkList(id);
        Result<List<WorkList>> result = new Result<>();
        result.setData(workList);
        return result;
    }

    @RequestMapping("/findAllUser")
    public Result<List<User>> findAllUser(){

        List<User> allUser = userService.findAllUser();
        Result<List<User>> result = new Result<>();
        result.setData(allUser);
        return result;
    }

    @RequestMapping("/findById")
    public Result<User> findById(String id){
        User user = userService.findById(id);
        Result<User> result = new Result<>();
        result.setData(user);
        return result;
    }
    @RequestMapping("/updateById")
    public Result<String> updateById(User user){
        int line = userService.updateById(user);
        Result<String> result = new Result<>();
        if (line != 1){
            result.setStatusCode("500");
            result.setMessage("修改失败");
        }
        return result;
    }

    @RequestMapping("/deleteById")
    public Result<String> deleteById(String id){
        userService.deleteById(id);
        return new Result<String>();
    }

    @RequestMapping("/save")
    public Result<String> save(User user) {
        userService.save(user);
        return new Result<>();
    }

}
