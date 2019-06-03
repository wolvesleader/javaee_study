package com.greenvillage.mapper;

import com.greenvillage.pojo.Student;

import java.util.List;

/**
 * author:quincy
 * Date:2019-03-18
 */
public interface StudentMapper {


    public List<Student> findAllStudent();

    Student findById(String id);

    int updateById(Student student);

    void deleteById(String id);

    Student findByNumberAndPassword(String number, String password);

    void registerStudent(Student student);
}