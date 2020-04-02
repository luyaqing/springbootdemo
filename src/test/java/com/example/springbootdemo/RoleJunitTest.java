package com.example.springbootdemo;

import com.example.springbootdemo.model.RoleInfo;
import com.example.springbootdemo.service.RoleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.management.relation.Role;
import java.util.UUID;

/**
 * @author BG362793
 * roleService 的测试
 */
public class RoleJunitTest extends SpringbootdemoApplicationTests{

    @Autowired
    private RoleService roleService;

    @Test
    public void testInsertAndGetRoleById(){

        RoleInfo roleInfo = new RoleInfo();

        roleInfo.setRoleId(UUID.randomUUID().toString().substring(0,8));
        roleInfo.setRoleName("role_name");
        roleInfo.setPermission("test_test_test");
        roleInfo.setComment("role_comment");

        roleService.insertRole(roleInfo);
        RoleInfo selectRoleInfo = roleService.getRoleById(roleInfo.getRoleId());

        assert selectRoleInfo != null;
        assert roleInfo.getRoleId().equals(selectRoleInfo.getRoleId());
        assert roleInfo.getRoleName().equals(selectRoleInfo.getRoleName());
        assert roleInfo.getPermission().equals(selectRoleInfo.getPermission());
        assert roleInfo.getComment().equals(selectRoleInfo.getComment());
    }


    @Test
    public void testUpdateRole(){

        RoleInfo roleInfo = new RoleInfo();

        roleInfo.setRoleId(UUID.randomUUID().toString().substring(0,8));
        roleInfo.setRoleName("role_name");
        roleInfo.setPermission("test_test_test");
        roleInfo.setComment("role_comment");
        roleService.insertRole(roleInfo);

        roleInfo.setRoleName("role_name_change");
        roleInfo.setPermission("test_test_change");
        roleInfo.setComment("role_comment_change");
        roleService.updateRole(roleInfo);

        RoleInfo selectRole = roleService.getRoleById(roleInfo.getRoleId());

        assert roleInfo.getRoleName().equals(selectRole.getRoleName());
        assert roleInfo.getPermission().equals(selectRole.getPermission());
        assert roleInfo.getComment().equals(selectRole.getComment());

    }

    @Test
    public void testDeleteById(){

        RoleInfo roleInfo = new RoleInfo();

        roleInfo.setRoleId(UUID.randomUUID().toString().substring(0,8));
        roleInfo.setRoleName("test_delete");
        roleInfo.setPermission("test_test_delete");
        roleInfo.setComment("test_delete");
        roleService.insertRole(roleInfo);

        roleService.deleteRoleById(roleInfo.getRoleId());
        RoleInfo selectInfo = roleService.getRoleById(roleInfo.getRoleId());

        assert selectInfo == null;
    }
}
