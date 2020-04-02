package com.example.springbootdemo;

import com.example.springbootdemo.model.UserInfo;
import com.example.springbootdemo.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @author BG362793
 * UserService的junit测试
 */
public class UserJunitTest extends SpringbootdemoApplicationTests{

    private static final String dateFormat = "yyyy-MM-dd HH:mm:ssX";
    @Autowired
    private UserService  userService;

    @Test
    public void testAddGetUserById(){
        UserInfo userInfo = createUser();
        //...
        userService.insertUser(userInfo);
        UserInfo selectUser = userService.getUserById(userInfo.getUserId());

        assert selectUser != null;
        assert userInfo.getUserId().equals(selectUser.getUserId());
        assert userInfo.getLoginId().equals(selectUser.getLoginId());
        assert userInfo.getUserName().equals(selectUser.getUserName());
        assert userInfo.getUserPwd().equals(selectUser.getUserPwd());
        assert userInfo.getStatus().equals(selectUser.getStatus());
        assert userInfo.getLastLoginTime().equals(selectUser.getLastLoginTime());
        assert userInfo.getDepartment().equals(selectUser.getDepartment());
        assert userInfo.getPhone().equals(selectUser.getPhone());

    }

    // 测试修改方法
    @Test
    public void testUpdateUserById(){
        UserInfo userInfo = createUser();
        userService.insertUser(userInfo);

        userInfo.setUserName(getRandomString(6)+"_test");
        userInfo.setDepartment(getRandomString(6)+"_test");
        userInfo.setLoginId(getRandomString(9)+"_test");
        userInfo.setPhone(getRandoNumber(11)+"_test");
        userInfo.setUserPwd(UUID.randomUUID().toString().substring(0,10)+"_test");
        userInfo.setStatus(String.valueOf(new Random().nextInt(2)));

        userService.updateUser(userInfo);
        UserInfo selectUser = userService.getUserById(userInfo.getUserId());

        assert selectUser != null;
        assert userInfo.getLoginId().equals(selectUser.getLoginId());
        assert userInfo.getUserName().equals(selectUser.getUserName());
        assert userInfo.getUserPwd().equals(selectUser.getUserPwd());
        assert userInfo.getStatus().equals(selectUser.getStatus());
        assert userInfo.getLastLoginTime().equals(selectUser.getLastLoginTime());
        assert userInfo.getDepartment().equals(selectUser.getDepartment());
        assert userInfo.getPhone().equals(selectUser.getPhone());

    }

    @Test
    public void testDeleteUserById(){

        UserInfo userInfo = createUser();
        userService.insertUser(userInfo);

        userService.deleteUserById(userInfo.getUserId());
        UserInfo actualUser = userService.getUserById(userInfo.getUserId());
        assert actualUser == null;
    }

    @Test
    public void testLoginIn(){

        UserInfo userInfo = createUser();
        userService.insertUser(userInfo);

        assert userService.loginMethod(userInfo.getLoginId(),userInfo.getUserPwd());
    }

    private static  UserInfo  createUser(){
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(getRandomString(32));
        userInfo.setUserName(getRandomString(15));
        userInfo.setDepartment(getRandomString(10));
        userInfo.setLoginId(getRandomString(15));
        userInfo.setPhone(getRandoNumber(11));
        userInfo.setUserPwd(UUID.randomUUID().toString().substring(0,10));
        userInfo.setStatus(String.valueOf(new Random().nextInt(2)));
        userInfo.setLastLoginTime(format.format(new Date()));

        return userInfo;
    }

    private static String getRandomString(int length){

        String str = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();

        StringBuffer  sb = new StringBuffer();
        for(int i=0; i<length; i++){

            int pos = random.nextInt(62);
            sb.append(str.charAt(pos));
        }

        return sb.toString();
    }

    private static String getRandoNumber(int length){

        String str = "0123456789";
        Random random = new Random();

        StringBuffer  sb = new StringBuffer();
        for(int i=0; i<length; i++){
            int pos = random.nextInt(10);
            sb.append(str.charAt(pos));
        }

        return sb.toString();
    }


}
