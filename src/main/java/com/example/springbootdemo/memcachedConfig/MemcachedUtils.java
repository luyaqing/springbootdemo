package com.example.springbootdemo.memcachedConfig;

import com.danga.MemCached.MemCachedClient;
import com.example.springbootdemo.util.SerializeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * memcached缓存的工具类
 */
@Component
public class MemcachedUtils {

    private static final Logger logger = LoggerFactory.getLogger(MemcachedUtils.class);
    @Autowired
    private MemCachedClient memCachedClient;


    //向缓存中 添加数据 如果已经存在 把之前的数据删除
    public boolean setValueToMemcache(String key, Object o){

        return setExp(key, o, null);
    }


    /**
     * 添加 数据到缓存中
     * @param key
     * @param value
     * @param expire
     * @return
     */
    public boolean setExp(String key, Object value, Date expire){

        boolean flag = false;
        try{
            flag=memCachedClient.set(key, value, expire);
        }catch (Exception e){
            //记录日志
            logger.info("memcache方法报错， Key值："+key+"\r\n");
        }
        return flag;

    }

    // 向 缓存中加入数据 加入过期时间
    public boolean setvalueToMemcacheWithExpire(String key, Object o, Date date){

        return setExp(key, o, date);
    }


    /**
     * 当缓存中不存在key时，add才会向缓存中添加一个键值对
     * @param key
     * @param o
     * @param expire
     */
    public boolean addData(String key, Object o, Date expire){

        return addExp(key, o, expire);
    }

    public boolean addExp(String key, Object o, Date expire){
        boolean flag = false;

        try{

            flag = memCachedClient.add(key, o, expire);
        }catch(Exception e){
            logger.info("Memcached addData 报错， Key值："+key+"\r\n");
        }

        return flag;
    }

    /**
     * 仅当键存在的时候  repplace命令才会替换
     * @param key
     * @param o
     * @return
     */
    public boolean replace(String key, Object o){

        return returnExp(key, o, null);
    }

    public boolean replace(String key, Object o, Date expire){

        return returnExp(key, o, expire);
    }

    public boolean returnExp(String key, Object o, Date expire){
        boolean flag = false;

        try{
            flag = memCachedClient.replace(key, o, expire);
        }catch(Exception e){
            logger.info("memcached returnExp 方法报错， key值："+key+"\r\n");
        }

        return flag;
    }

    /**
     *  get 方法 获取缓存中的数据
     * @param key
     * @return
     */
    public Object getObejctBykey(String key){
        Object obj = null;
        try{
            obj = memCachedClient.get(key);
        }catch (Exception e){
            logger.info("Memcached get 方法报错， key+"+key+"\r\n");
        }
        return obj;
    }

    /**
     * 删除缓存中的值
     * @param key
     * @return
     */
    public boolean del(String key){
        return delExp(key,null);
    }

    public boolean del(String key, Date expire){
        return delExp(key,expire);
    }

    public boolean delExp(String key, Date expire){
        boolean  flag = false;

        try{
            flag = memCachedClient.delete(key,expire);
        }catch (Exception e){
            logger.info("Memcached delExp 方法报错， key+"+key+"\r\n");
        }
        return flag;
    }


    public boolean flushAll(){
        boolean flag = false;
        try{
            flag = memCachedClient.flushAll();
        }catch (Exception e){
            logger.info("Memcached flushAll 方法报错!");
        }
        return flag;
    }


}
