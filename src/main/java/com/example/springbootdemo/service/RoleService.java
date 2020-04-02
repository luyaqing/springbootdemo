package com.example.springbootdemo.service;

import com.example.springbootdemo.model.RoleInfo;

/**
 * @author BG362793
 */
public interface RoleService {

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
