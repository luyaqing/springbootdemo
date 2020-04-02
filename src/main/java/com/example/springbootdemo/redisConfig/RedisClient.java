package com.example.springbootdemo.redisConfig;

import com.example.springbootdemo.util.SerializeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author BG362793
 * redis 工具类
 */
@Component
public class RedisClient {


    private static final Logger log = LoggerFactory.getLogger(RedisClient.class);

    /** 数据有效期 */
    private int expireTime = 7 * 24 * 3600;

    @Autowired
    private JedisPool jedisPool;

    /**
     * 缓存中获取数据
     * @param key
     * @return
     */
    public String getValueByKey(String key){

        Jedis jedis = jedisPool.getResource();
        String str  = "";
        try{
            str = jedis.get(key);
        }catch(Exception e){
            jedis.close();
        }finally {
            jedis.close();
        }

        return str;
    }

    /**
     * 放到 缓存中 默认时间是 7days
     * @param key
     * @param value
     * @return
     */
    public String setValueToRedis(String key, String value){
        String str = "";
        setex(key, expireTime, value);
        return str;
    }

    /**
     * 将值放到redis中
     *
     * @param key
     * @param value
     * @return
     */
    public String setex(String key, int seconds, String value) {
        Jedis jedis = jedisPool.getResource();
        String str = "";

        try {
            str = jedis.setex(key, seconds, value);
        } catch (Exception e) {

            jedis.close();
        } finally {
            jedis.close();
        }

        return str;
    }


    /**
     * 存入 value 为 Map的值
     * @param key
     * @param values
     */
    public void setMapValue(String key, Map<String, String> values) {
        Jedis jedis = jedisPool.getResource();
        try {

            jedis.hmset(key, values);
        } catch (Exception e) {

            jedis.close();
        } finally {
            jedis.close();
        }
    }

    /**
     * 获得Map的值
     * @param key
     * @param field
     * @return
     */
    public Optional<String> getMapValue(String key, String field) {
        Jedis jedis = jedisPool.getResource();
        List<String> values;
        try {
            values = jedis.hmget(key, field);

            if (null != values && values.size() == 1) {
                return Optional.ofNullable(values.get(0));
            }
        } catch (Exception e) {

            jedis.close();
        } finally {
            jedis.close();
        }
        return Optional.empty();
    }


    /**
     * 删除redis中的数据
     *
     * @param key
     */
    public void removeValueByKey(String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.del(key);

        } catch (Exception e) {

            jedis.close();
        } finally {
            jedis.close();
        }
    }

    /**
     * 自定义key
     * @param t
     * @param id
     * @param <T>
     * @return
     */
    public <T> String buildKey(T t,String id) {
        if (t == null) {
            return "";
        }
        Class clazz = t.getClass();
        String poName = clazz.getSimpleName();

        return "test_"+id;
    }


    public void setObject(String key, int seconds, Object obj) {

        Jedis jedis = jedisPool.getResource();

        try {
            jedis.setex(key.getBytes(), seconds, SerializeUtil.serialize(obj));
        } catch (Exception e) {

            jedis.close();
        } finally {
            jedis.close();
        }
    }

    public void setObject(String key, Object obj) {
        setObject(key, expireTime, obj);
    }

    public Object getObject(String key) {

        Jedis jedis = jedisPool.getResource();
        byte[] value = new byte[0];
        try {
            value = jedis.get(key.getBytes());
        } catch (Exception e) {

            jedis.close();
        } finally {
            jedis.close();
        }
        if (value != null) {
            return SerializeUtil.unserialize(value);
        }

        return null;
    }


    public void delKey(String key){
        Jedis jedis = jedisPool.getResource();

        try {
            jedis.del(key);
        }finally{
            jedis.close();
        }
    }

}
