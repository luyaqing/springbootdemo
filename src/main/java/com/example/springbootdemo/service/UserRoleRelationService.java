package com.example.springbootdemo.service;

import com.example.springbootdemo.model.RoleInfo;
import com.example.springbootdemo.model.UserRoleRelation;

/**
 * @author BG362793
 */
public interface UserRoleRelationService {


    // 根据id查询 关联关系
    UserRoleRelation getRelationById(String id);

    //添加 关联关系
    void insertRelation(UserRoleRelation relation);

    //修改关系
    void updateRelation(UserRoleRelation relation);

    //删除关联关系
    void  deleteRelationById(String id);
}
