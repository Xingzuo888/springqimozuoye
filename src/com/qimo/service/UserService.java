package com.qimo.service;

import com.qimo.dao.UserMapper;
import com.qimo.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    public User queryUser(User user) {
        return userMapper.queryUser(user);
    }
}
