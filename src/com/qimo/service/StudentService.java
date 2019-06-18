package com.qimo.service;

import com.qimo.dao.StudentMapper;
import com.qimo.po.Page;
import com.qimo.po.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentService {

    @Autowired(required = false)
    private StudentMapper studentMapper;

    public void insertStudent(Student student) {
        studentMapper.insertStudent(student);
    }

    public Student getStudentById(Integer id) {
        return studentMapper.getStudentById(id);
    }

    public void updateStudent(Student student) {
        studentMapper.updateStudent(student);
    }

    public void deleteStudent(Integer id) {
        studentMapper.deleteStudent(id);
    }

    public List<Student> queryStudent(Page page) {
        return studentMapper.queryStudent(page);
    }

    public int queryStudentCount() {
        List<Student> list = studentMapper.queryStudentCount();
        return list.size();
    }

    public List<Student> queryStudentAll() {
        List<Student> list = studentMapper.queryStudentCount();
        return list;
    }
}
