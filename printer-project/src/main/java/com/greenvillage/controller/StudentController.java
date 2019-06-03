package com.greenvillage.controller;

import com.greenvillage.pojo.Result;
import com.greenvillage.pojo.Student;
import com.greenvillage.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * author:quincy
 * Date:2019-03-18
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @RequestMapping("/studentRecharge")
    public Result<Integer> studentRecharge(String recharge,String id,HttpServletRequest request ){
        Student dbStudent = studentService.findById(id);
        if (dbStudent != null){
            BigDecimal bigDecimal = new BigDecimal(dbStudent.getAccountBalance());
            BigDecimal bigDecimal1 = new BigDecimal(recharge);
            dbStudent.setAccountBalance(bigDecimal.add(bigDecimal1).toString());
        }
        Integer result = studentService.updateById(dbStudent);
        Student loginStudent = (Student) request.getSession().getAttribute("loginStudent");
        loginStudent.setAccountBalance(dbStudent.getAccountBalance());

        Result<Integer> sult = new Result<>();
        sult.setData(result);
        return sult;

    }

    @RequestMapping("/registerStudent")
    public Result<String> registerStudent(Student student){

        //student.setNumber(UUID.randomUUID().toString());
        student.setAccountBalance("0");
        studentService.registerStudent(student);

        Result<String> result = new Result<>();

        return result;
    }


    @RequestMapping("/findAllStudent")
    public Result<List<Student>> findAllStudent(){

        List<Student> allStudent = studentService.findAllStudent();
        Result<List<Student>> result = new Result<>();
        result.setData(allStudent);

        return result;
    }

    @RequestMapping("/findById")
    public Result<Student> findById(String id){
        Student studentPoint = studentService.findById(id);
        Result<Student> result = new Result<>();
        result.setData(studentPoint);
        return result;
    }
    @RequestMapping("/updateById")
    public Result<String> updateById(Student student){
        int line = studentService.updateById(student);
        Result<String> result = new Result<>();
        if (line != 1){
            result.setStatusCode("500");
            result.setMessage("修改失败");
        }
        return result;
    }

    @RequestMapping("/deleteById")
    public Result<String> deleteById(String id){
        studentService.deleteById(id);
        return new Result<String>();
    }
}
