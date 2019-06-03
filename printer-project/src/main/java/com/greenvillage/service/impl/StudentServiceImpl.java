package com.greenvillage.service.impl;

import com.greenvillage.mapper.StudentMapper;
import com.greenvillage.pojo.Student;
import com.greenvillage.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author:quincy
 * Date:2019-03-18
 */

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> findAllStudent() {
        List<Student> allStudent = studentMapper.findAllStudent();
        return allStudent;
    }

    @Override
    public Student findById(String id) {
        return studentMapper.findById(id);
    }

    @Override
    public int updateById(Student student) {
        return studentMapper.updateById(student);
    }

    @Override
    public void deleteById(String id) {
        studentMapper.deleteById(id);
    }

    @Override
    public Student findByNumberAndPassword(String number, String password) {
        return studentMapper.findByNumberAndPassword(number, password);
    }

    @Override
    public void registerStudent(Student student) {
        studentMapper.registerStudent(student);
    }
}
