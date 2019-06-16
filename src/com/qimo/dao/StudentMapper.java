package com.qimo.dao;

import com.qimo.po.Page;
import com.qimo.po.Student;

import java.util.List;

public interface StudentMapper {
    public void insertStudent(Student student);

    public void updateStudent(Student student);

    public void deleteStudent(Integer id);

    public List<Student> queryStudent(Page page);

    public Student getStudentById(Integer id);

    public List<Student> queryStudentCount();
}
