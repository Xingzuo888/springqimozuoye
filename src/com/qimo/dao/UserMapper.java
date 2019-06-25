package com.qimo.dao;

import com.qimo.po.User;

public interface UserMapper {
    public User queryUser(User user);

    public boolean insertUser(User user);

    public void updateUser(User user);

    public void deleteUser(User user);
}
