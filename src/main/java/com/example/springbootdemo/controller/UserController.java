package com.example.springbootdemo.controller;

import com.example.springbootdemo.model.JsonResults;
import com.example.springbootdemo.model.RoleInfo;
import com.example.springbootdemo.model.UserInfo;
import com.example.springbootdemo.model.UserRoleRelation;
import com.example.springbootdemo.service.RoleService;
import com.example.springbootdemo.service.UserRoleRelationService;
import com.example.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BG362793
 *  用户控制器
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleRelationService  userRoleRelationService;

    //登录模块
    @RequestMapping(value = "user/login",method = RequestMethod.GET)
    public JsonResults LoginIn(String loginId, String pwd){

        JsonResults jsonResults = new JsonResults();
        Map<String, Object> map = new HashMap<String, Object>();
        boolean  flag = userService.loginMethod(loginId, pwd);

        if(flag){
            map.put("msg","登陆成功！");
        }else{
            map.put("msg","登录失败，密码错误！");
            jsonResults.setSuccess(false);
        }

        jsonResults.setMessage(map);
        return jsonResults;
    }


    //查询所有的数据
    @RequestMapping(value = "user/getUserList", method = RequestMethod.GET)
    public JsonResults<UserInfo> getUserList(){
        JsonResults jsonResults = new JsonResults();
        Map<String, Object> map = new HashMap<>();
        List<UserInfo> userList = userService.getUserList();

        jsonResults.setData(userList);
        jsonResults.setTotalCount((long)userList.size());

        map.put("msg", "user/getUserList success");
        jsonResults.setMessage(map);
        //throw new NullPointerException("发生了 NullPointerException 异常");
        return jsonResults;
    }
    //查询用户数据
    @RequestMapping(value = "user/getUser",method = RequestMethod.GET)
    public JsonResults<UserInfo> getUserById(String id){
        JsonResults jsonResults = new JsonResults();
        Map<String, Object> map = new HashMap<String, Object>();

        UserInfo user=userService.getUserById(id);
        List<UserInfo> list = new ArrayList<>();
        list.add(user);
        jsonResults.setData(list);

        map.put("msg", "getUserById success！");
        jsonResults.setMessage(map);
        jsonResults.setTotalCount( (long)list.size() );
        return jsonResults;
    }

    //新增用户
    @RequestMapping(value = "user/addUser")
    public JsonResults insetUser(UserInfo info){
        JsonResults jsonResults = new JsonResults();
        Map<String, Object> map = new HashMap<String, Object>();

        userService.insertUser(info);
        map.put("msg", "insertUser success！");

        jsonResults.setMessage(map);
        return jsonResults;
    }

    //删除用户
    @RequestMapping(value = "user/deleteUser")
    public JsonResults deleteUser(String id){
        JsonResults jsonResults = new JsonResults();
        Map<String, Object> map = new HashMap<String, Object>();

        userService.deleteUserById(id);
        map.put("msg", "deleteUser success！");

        jsonResults.setMessage(map);
        return jsonResults;
    }

    //修改用户
    @RequestMapping(value = "user/updateUser")
    public JsonResults updateUser(UserInfo info){
        JsonResults jsonResults = new JsonResults();
        Map<String, Object> map = new HashMap<String, Object>();

        userService.updateUser(info);
        map.put("msg", "updateUser success！");
        jsonResults.setMessage(map);
        return jsonResults;
    }



    //查询角色
    @RequestMapping(value = "role/getRole",method = RequestMethod.GET)
    public JsonResults getRelationById(String id){
        JsonResults jsonResults = new JsonResults();
        Map<String, Object> map = new HashMap<String, Object>();

        RoleInfo role=roleService.getRoleById(id);
        List<RoleInfo> list = new ArrayList<>();
        list.add(role);

        jsonResults.setData(list);
        jsonResults.setTotalCount((long)list.size());
        map.put("msg", "role/getRole success!");

        jsonResults.setMessage(map);
        return jsonResults;
    }

    //添加角色
    @RequestMapping(value = "role/addRole",method = RequestMethod.GET)
    public JsonResults addRole(RoleInfo role){
        JsonResults jsonResults = new JsonResults();
        Map<String, Object> map = new HashMap<String, Object>();

        roleService.insertRole(role);
        map.put("msg", "role/addRole success!");

        jsonResults.setMessage(map);
        return jsonResults;
    }

    //修改角色
    @RequestMapping(value = "role/updateRole",method = RequestMethod.GET)
    public JsonResults updateRole(RoleInfo role){
        JsonResults jsonResults = new JsonResults();
        Map<String, Object> map = new HashMap<String, Object>();
        roleService.updateRole(role);

        map.put("msg", "role/updateRole success!");
        jsonResults.setMessage(map);
        return jsonResults;
    }

    //删除角色
    @RequestMapping(value = "role/deleteRole",method = RequestMethod.GET)
    public JsonResults deleteRoleById(String id){
        JsonResults jsonResults = new JsonResults();
        Map<String, Object> map = new HashMap<String, Object>();
        roleService.deleteRoleById(id);

        map.put("msg", "role/deleteRole success!");
        jsonResults.setMessage(map);
        return jsonResults;
    }



    //查询 用户角色关联关系
    @RequestMapping(value = "userRoleRelation/getRelation",method = RequestMethod.GET)
    public JsonResults getUserRoleRelation(String id){
        JsonResults jsonResults = new JsonResults();
        Map<String, Object> map = new HashMap<String, Object>();

        UserRoleRelation role=userRoleRelationService.getRelationById(id);
        List<UserRoleRelation>  list = new ArrayList<>();
        list.add(role);
        jsonResults.setData(list);
        jsonResults.setTotalCount((long)list.size());

        map.put("msg", "userRoleRelation/getRelation");
        jsonResults.setMessage(map);
        return jsonResults;
    }

    //添加 用户角色关联关系
    @RequestMapping(value = "userRoleRelation/addRelation",method = RequestMethod.GET)
    public void addRelation(UserRoleRelation relation){

        userRoleRelationService.insertRelation(relation);
    }

    //修改 用户角色关联关系
    @RequestMapping(value = "userRoleRelation/updateRelation",method = RequestMethod.GET)
    public void updateRelation(UserRoleRelation relation){

        userRoleRelationService.updateRelation(relation);
    }

    //删除 用户角色关联关系
    @RequestMapping(value = "userRoleRelation/deleteRelation",method = RequestMethod.GET)
    public void deleteRelation(String id){

        userRoleRelationService.deleteRelationById(id);
    }
}

