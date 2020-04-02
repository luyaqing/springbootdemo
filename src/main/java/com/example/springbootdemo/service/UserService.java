package com.example.springbootdemo.service;

import com.example.springbootdemo.model.UserInfo;

import java.util.List;

/**
 * @author BG362793
 */
public interface UserService {

    //id 查询
    UserInfo getUserById(String id);

    //查询所有的用户数据
    List<UserInfo> getUserList();

    //name 查询
    UserInfo getUserByName(String name);

    //新增
    void insertUser(UserInfo user);

    //修改
    void updateUser(UserInfo user);

    //删除
    void deleteUserById(String id);

    //登录
    boolean loginMethod(String loginId, String pwd);


}
