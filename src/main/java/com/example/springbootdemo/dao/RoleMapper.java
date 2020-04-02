package com.example.springbootdemo.dao;

import com.example.springbootdemo.model.RoleInfo;
import org.springframework.stereotype.Repository;

/**
 * @author BG362793
 */
@Repository
public interface RoleMapper {

    //id  查询角色
    RoleInfo getRoleById(String id);

    //name 查询角色
    RoleInfo getRoleByName(String name);

    //添加角色
    void insertRole(RoleInfo role);

    //修改角色
    void updateRole(RoleInfo role);

    //删除角色
    void  deleteRoleById(String id);
}
