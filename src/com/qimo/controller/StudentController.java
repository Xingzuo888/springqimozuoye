package com.qimo.controller;

import com.qimo.po.Page;
import com.qimo.po.Student;
import com.qimo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @RequestMapping(value = "/B_insert",method = RequestMethod.GET)
    public String insertStudent(Map<String, Object> map) {
        Map<String,Object> sex_map = new HashMap<>();
        sex_map.put("w","女");
        sex_map.put("m","男");
        map.put("sex", sex_map);
        map.put("student", new Student());
        return "add";
    }

    @RequestMapping(value = "/B_insert/{id}",method = RequestMethod.GET)
    public String insertStudent(@PathVariable("id") Integer id, Map<String, Object> map) {
        Map<String,Object> sex_map = new HashMap<>();
        sex_map.put("w","女");
        sex_map.put("m","男");
        map.put("sex", sex_map);
        map.put("student", studentService.getStudentById(id));
        return "add";
    }

    @RequestMapping(value = "/B_insert", method = RequestMethod.POST)
    public String insertStudent(@Valid Student student, BindingResult result, Map<String, Object> map) {
        if (result.getErrorCount() > 0) {
            for (FieldError error : result.getFieldErrors()) {
                System.out.println(error.getField() + "--" + error.getDefaultMessage());
            }
            Map<String,Object> sex_map = new HashMap<>();
            sex_map.put("w","女");
            sex_map.put("m","男");
            map.put("sex", sex_map);
            map.put("student", student);
            return "add";
        }
        studentService.insertStudent(student);
        return "redirect:/student/B_query";
    }

    @RequestMapping(value = "/B_insert",method = RequestMethod.PUT)
    public String updateStudent(Student student) {
        studentService.updateStudent(student);
        return "redirect:/student/B_query";
    }

    @RequestMapping(value = "B_delete/{id}",method = RequestMethod.DELETE)
    public String deleteStudent(@PathVariable("id") Integer id) {
        studentService.deleteStudent(id);
        return "redirect:/student/B_query";
    }

    @RequestMapping(value = "B_query",method = RequestMethod.GET)
    public String queryStudent(HttpSession session,Map<String, Object> map) {
        Page page = new Page();
        page.setCount(0);
        page.setSize(5);
        List<Student> list=studentService.queryStudent(page);
        map.put("page", page);
        map.put("count", studentService.queryStudentCount());
        map.put("student_list", list);
        session.setAttribute("Page",page);
        return "list";
    }

    @RequestMapping(value = "B_queryUp",method = RequestMethod.GET)
    public String queryStudentUp(HttpSession session,Map<String, Object> map) {
        Page page = new Page();
        Page s = (Page) session.getAttribute("Page");
        if (s.getCount() - s.getSize() >= 0) {
            page.setCount(s.getCount()-s.getSize());
        }else {
            page.setCount(s.getCount());
        }
        page.setSize(s.getSize());
        List<Student> list=studentService.queryStudent(page);
        map.put("page", page);
        map.put("count", studentService.queryStudentCount());
        map.put("student_list", list);
        session.setAttribute("Page",page);
        return "list";
    }

    @RequestMapping(value = "B_queryDown",method = RequestMethod.GET)
    public String queryStudentDown(HttpSession session,Map<String, Object> map) {
        Page page = new Page();
        Page s = (Page) session.getAttribute("Page");
        int count = studentService.queryStudentCount();
        System.out.println("---"+count);
        if (s.getCount() >= 0 && (s.getCount()+ s.getSize()) < count) {
            page.setCount(s.getCount() + s.getSize());
            System.out.println("--->0" + page.getCount());
        } else {
            page.setCount(s.getCount());
            System.out.println("---<0" + page.getCount());
        }
        page.setSize(s.getSize());
        List<Student> list=studentService.queryStudent(page);
        map.put("page", page);
        map.put("count", count);
        map.put("student_list", list);
        session.setAttribute("Page",page);
        return "list";
    }

}
