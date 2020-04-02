package com.example.springbootdemo;

import com.example.springbootdemo.model.RoleInfo;
import com.example.springbootdemo.redisConfig.RedisClient;
import com.example.springbootdemo.service.RoleService;
import com.example.springbootdemo.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author  BG362793
 * redis 单元测试
 *
 */
public class RedisJunitTest extends SpringbootdemoApplicationTests{

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private RoleService roleService;

    @Test
    public void testGetFromRedisCache(){

        RoleInfo roleInfo = new RoleInfo();

        roleInfo.setRoleId("4545");
        roleInfo.setRoleName("junit_test");
        roleInfo.setPermission("permission_test");
        roleInfo.setComment("comment_test");

        roleService.insertRole(roleInfo);

        RoleInfo selectRole = roleService.getRoleById(roleInfo.getRoleId());
        redisClient.setObject(selectRole.getRoleId(), selectRole);
        RoleInfo redisRole = (RoleInfo) redisClient.getObject(selectRole.getRoleId());

        assert redisRole != null;
        roleService.deleteRoleById("4545");
    }
}
