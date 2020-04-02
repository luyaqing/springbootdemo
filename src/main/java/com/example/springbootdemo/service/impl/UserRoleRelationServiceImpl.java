package com.example.springbootdemo.service.impl;

import com.danga.MemCached.MemCachedClient;
import com.example.springbootdemo.dao.UserRoleRelationMapper;
import com.example.springbootdemo.memcachedConfig.MemcachedUtils;
import com.example.springbootdemo.model.UserRoleRelation;
import com.example.springbootdemo.service.UserRoleRelationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author BG362793
 *
 */
@Component
public class UserRoleRelationServiceImpl implements UserRoleRelationService {
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRoleRelationMapper relationMapper;

    @Autowired
    private MemcachedUtils memcachedUtils;

    /**
     *  加入memcached 缓存 还有问题 应该序列化一下  object  序列化一下
     * @param id
     * @return
     */
    @Override
    public UserRoleRelation getRelationById(String id){

        Object memcachedRelation =memcachedUtils.getObejctBykey(id);
        if(memcachedRelation != null){
            logger.info("在 Memcache缓存中 查到了 key："+id+"的数据！");
            return null;
        }

        UserRoleRelation selectRelation = relationMapper.getRelationById(id);
        memcachedUtils.setValueToMemcache(id,selectRelation);
        logger.info("memcached 缓存中加入了数据，key："+id+"\r\n");
        return selectRelation;
    }

    @Override
    public void insertRelation(UserRoleRelation relation){

        relationMapper.insertRelation(relation);
    }

    @Override
    public void updateRelation(UserRoleRelation relation){

        relationMapper.updateRelation(relation);
    }

    @Override
    public void deleteRelationById(String id){

        relationMapper.deleteRelationById(id);
    }
}
