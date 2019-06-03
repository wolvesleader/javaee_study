package com.greenvillage.controller;

import com.greenvillage.pojo.PrintPoint;
import com.greenvillage.pojo.Result;
import com.greenvillage.pojo.Student;
import com.greenvillage.service.PrintPointService;
import com.greenvillage.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * author:quincy
 * Date:2019-03-25
 */
@RestController
@RequestMapping("/custom")
public class CustomLoginController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private PrintPointService printPointService;

    @RequestMapping("/studentLogout")
    public Result<Student> studentLogout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("loginStudent");
        Result<Student> result = new Result<>();
        result.setMessage("注销成功");
        return result;
    }


    @RequestMapping("/studentLogin")
    public Result<Student> studentLogin(Student student, HttpServletRequest request){
        Result<Student> result = new Result<>();
        HttpSession session = request.getSession();
        Student studentFromDb = studentService.findByNumberAndPassword(student.getNumber(),student.getPassword());
        if (studentFromDb != null){
            result.setMessage("成功");
            result.setStatusCode("200");
            result.setData(studentFromDb);
            session.setAttribute("loginStudent",studentFromDb);
        }else {
            result.setMessage("失败");
            result.setStatusCode("404");
            result.setData(studentFromDb);
        }
        return result;
    }

    @RequestMapping("/printerLogin")
    public Result<PrintPoint> printerLogin(PrintPoint printPoint, HttpServletRequest request){
        Result<PrintPoint> result = new Result<>();
        HttpSession session = request.getSession();
        PrintPoint printerPointFromDb = printPointService.findByPrinterNumberAndPassword(printPoint.getPrinterNumber(),printPoint.getPassword());
        if (printerPointFromDb != null){
            result.setMessage("成功");
            result.setStatusCode("200");
            result.setData(printerPointFromDb);
            session.setAttribute("loginPrintPoint",printerPointFromDb);
        }else {
            result.setMessage("打印机不存在");
            result.setStatusCode("404");
            result.setData(printerPointFromDb);
        }
        return result;
    }
}
