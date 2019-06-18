package com.qimo.controller;

import com.qimo.po.Page;
import com.qimo.po.Student;
import com.qimo.service.StudentService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
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
    @RequestMapping("C_queryStudent")
    public void queryStudent(HttpServletResponse response) throws IOException {
        List<Student> list = studentService.queryStudentAll();
        JSONArray jsonArray = new JSONArray();
        for (Student student : list) {
            jsonArray.put(new JSONObject(student));
        }
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.println(jsonArray);
        writer.close();
    }

    @RequestMapping("C_insert")
    public void insert(HttpServletRequest request,HttpServletResponse response) throws JSONException, IOException {
        JSONObject jsonObject = new JSONObject();
        if (!request.getParameter("sno").isEmpty() && !request.getParameter("sname").isEmpty() && !request.getParameter("sex").isEmpty()) {
            Student student = new Student();
            student.setSno(request.getParameter("sno"));
            student.setSname(request.getParameter("sname"));
            student.setSex(request.getParameter("sex"));

            studentService.insertStudent(student);
            jsonObject.put("status",0);
            jsonObject.put("message","添加成功！");
        }else {
            jsonObject.put("status",-1);
            jsonObject.put("message","输入学号、姓名、性别不能为空!");
        }
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.println(jsonObject);
        writer.close();
    }

    @RequestMapping("C_update")
    public void update(HttpServletRequest request,HttpServletResponse response) throws JSONException, IOException {
        JSONObject jsonObject = new JSONObject();
        if (!request.getParameter("id").isEmpty() && !request.getParameter("sno").isEmpty() && !request.getParameter("sname").isEmpty() && !request.getParameter("sex").isEmpty()) {
            Student student = new Student();
            student.setId(Byte.valueOf(request.getParameter("id").trim()));
            student.setSno(request.getParameter("sno"));
            student.setSname(request.getParameter("sname"));
            student.setSex(request.getParameter("sex"));

            studentService.updateStudent(student);
            jsonObject.put("status",0);
            jsonObject.put("message","修改成功！");
        }else {
            jsonObject.put("status",-1);
            jsonObject.put("message","输入学号、姓名、性别不能为空!");
        }
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.println(jsonObject);
        writer.close();
    }

    @RequestMapping("C_delete")
    public void delete(HttpServletRequest request,HttpServletResponse response) throws JSONException, IOException {
        JSONObject jsonObject = new JSONObject();
        if (!request.getParameter("id").isEmpty()) {

            studentService.deleteStudent(Integer.valueOf(request.getParameter("id").trim()));
            jsonObject.put("status",0);
            jsonObject.put("message","删除成功！");
        }else {
            jsonObject.put("status",-1);
            jsonObject.put("message","删除失败!");
        }
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.println(jsonObject);
        writer.close();
    }

}
