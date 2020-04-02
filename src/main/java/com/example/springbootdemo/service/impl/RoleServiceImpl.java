package com.example.springbootdemo.service.impl;

import com.example.springbootdemo.dao.RoleMapper;
import com.example.springbootdemo.model.RoleInfo;
import com.example.springbootdemo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author
 *
 */
@Component
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public RoleInfo getRoleById(String id){

        RoleInfo role = roleMapper.getRoleById(id);
        return role;
    }

    @Override
    public RoleInfo getRoleByName(String name){

        RoleInfo role = roleMapper.getRoleByName(name);
        return role;
    }

    @Override
    public void insertRole(RoleInfo role){

        roleMapper.insertRole(role);
    }

    @Override
    public void updateRole(RoleInfo role){

        roleMapper.updateRole(role);
    }

    @Override
    public void deleteRoleById(String id){

        roleMapper.deleteRoleById(id);
    }
}
