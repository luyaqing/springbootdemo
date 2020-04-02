package com.example.springbootdemo.dao;

import com.example.springbootdemo.model.UserRoleRelation;
import org.springframework.stereotype.Repository;

/**
 * @author BG362793
 * 用户角色关联关系
 */
@Repository
public interface UserRoleRelationMapper {

    //查询 用户角色关联信息
    UserRoleRelation getRelationById(String id);

    //添加信息
    void insertRelation(UserRoleRelation relation);

    //修改信息
    void updateRelation(UserRoleRelation relation);

    //删除 关联信息
    void deleteRelationById(String id);

}
