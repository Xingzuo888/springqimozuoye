package com.qimo.controller;

import com.qimo.po.Page;
import com.qimo.po.User;
import com.qimo.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserContrller {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(Map<String, Object> map) {
        map.put("user", new User());
        return "login";
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(Map<String, Object> map) {
        map.put("user", new User());
        return "register";
    }

    @RequestMapping(value = "/B_insert",method = RequestMethod.POST)
    public String insertUser(@Valid User user, BindingResult result, Map<String, Object> map){
        System.out.println("-----"+user.getUser_code());
        if (result.getErrorCount() > 0) {
            for (FieldError error : result.getFieldErrors()) {
                System.out.println(error.getField() + "--" + error.getDefaultMessage());
            }
            map.put("user", user);
            return "register";
        }else {
            if (userService.insertUser(user)) {
                map.put("insertResult", "注册成功！");
                return "redirect:/user/login";
            } else {
                System.out.println("注册失败！");
                map.put("insertResult", "注册失败！");
                map.put("user", new User());
                return "register";
            }

        }
    }

    @RequestMapping(value = "/B_query",method = RequestMethod.POST)
    public String queryUser(@Valid User user, BindingResult result, Map<String, Object> map){
        System.out.println("-----"+user.getUser_code());
        if (result.getErrorCount() > 0) {
            for (FieldError error : result.getFieldErrors()) {
                System.out.println(error.getField() + "--" + error.getDefaultMessage());
            }
            map.put("user", user);
            return "login";
        }else {
            if (userService.queryUser(user) != null) {

                return "redirect:/student/B_query";
            } else {
                System.out.println("未找到该用户！");
                map.put("queryResult", "未找到该用户！");
                map.put("user", new User());
                return "login";
            }

        }
    }

    @RequestMapping("/C_query")
    public void queryUser(HttpServletRequest request, HttpServletResponse response) throws JSONException {
        User user = new User();
        JSONObject jsonObject= new JSONObject();
        PrintWriter printWriter;
        if (!request.getParameter("user_code").isEmpty() && !request.getParameter("user_password").isEmpty()) {
            user.setUser_code(request.getParameter("user_code"));
            user.setUser_password(request.getParameter("user_password"));
            User u = userService.queryUser(user);

            if (u != null) {
                jsonObject = new JSONObject(u);
                jsonObject.put("status",0);
            } else {
                jsonObject.put("error_message", "未找到该用户！");
                jsonObject.put("status",-1);
            }
        } else {
            jsonObject.put("error_message", "账户或密码不能为空");
            jsonObject.put("status",-1);
        }
        response.setContentType("application/json;charset=utf-8");
        try {
            printWriter = response.getWriter();
            printWriter.println(jsonObject);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
