package com.example.springbootdemo.dao;

import com.example.springbootdemo.model.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author BG362793
 */
@Repository
public interface UserMapper {

    //id 查询
    UserInfo getUserById(String id);

    //查询所有的用户
    List<UserInfo> getUserList();;
    //name 查询
    UserInfo getUserByName(String name);

    //loginId 查询
    UserInfo getUserByLoginId(String loginId);

    //新增
    void insertUser(UserInfo user);

    //修改
    void updateUser(UserInfo user);

    //删除
    void deleteUserById(String id);


}
