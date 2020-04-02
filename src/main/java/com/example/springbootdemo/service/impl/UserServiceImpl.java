package com.example.springbootdemo.service.impl;

import com.example.springbootdemo.dao.UserMapper;
import com.example.springbootdemo.model.UserInfo;
import com.example.springbootdemo.redisConfig.RedisClient;
import com.example.springbootdemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author BG362793
 */
@Component
public class UserServiceImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisClient redisClient;

    // 通过id查询用户信息
    @Override
    public UserInfo getUserById(String id){

        //先从 缓存中查找，没有的话：去数据库中查找 然后放入缓存中；
        UserInfo redisUser = (UserInfo) redisClient.getObject(id);

        if(redisUser != null){
            logger.info("从redis缓存中取出"+redisUser.toString());
            return redisUser;
        }

        UserInfo selectUser=userMapper.getUserById(id);
        redisClient.setObject(selectUser.getUserId(),selectUser);
        logger.info("存入redis缓存中"+selectUser.toString());

        return selectUser;
    }

    @Override
    public List<UserInfo> getUserList(){
        return userMapper.getUserList();
    }
    //通过 用户名查询用户信息
    @Override
    public UserInfo getUserByName(String name){

        UserInfo user=userMapper.getUserByName(name);
        return user;
    }

    // 添加 用户
    @Override
    public void insertUser(UserInfo user){
        userMapper.insertUser(user);
    }

    //修改 用户  加入redis 缓存
    @Override
    public  void updateUser(UserInfo user){
        /**
         * 先查询缓存中是否存在该对象，存在的话  删除缓存，成功的话，添加缓存
         */
        UserInfo redisUser = (UserInfo) redisClient.getObject(user.getUserId());

        if(redisUser != null){
            redisClient.delKey(user.getUserId());
            logger.info("修改数据的时候，删除了缓存的中数据,id:"+user.getUserId());
        }

        userMapper.updateUser(user);
        redisClient.setObject(user.getUserId(),user);
        logger.info("修改的数据放入了缓存中："+user.toString());
    }

    //删除用户 根据id
    @Override
    public void  deleteUserById(String id){

        userMapper.deleteUserById(id);

        // 删除完之后  同时也删除缓存中的数据
        UserInfo redisUser = (UserInfo) redisClient.getObject(id);
        if(redisUser != null){
            redisClient.delKey(id);
            logger.info("删除数据的时候删除了redis中的缓存：id："+id);
        }
    }

    //登录 验证
    @Override
    public boolean loginMethod(String loginId, String pwd){

        UserInfo userInfo = userMapper.getUserByLoginId(loginId);

        if(userInfo.getUserPwd().equals(pwd)){
            return true;
        }
        return false;
    }


}
