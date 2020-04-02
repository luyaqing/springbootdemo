package com.example.springbootdemo.redisConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author  BG362793
 * redis 配置
 */

@Component
@EnableCaching
public class RedisConfig {

    private static Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.database}")
    private int database;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.jedis.pool.min-idle}")
    private int minIdle;

    @Value("${spring.redis.timeout}")
    private int timeout;



    @Bean
    public JedisPool redisPoolFactory(){
        logger.info("jedisPool autowired success!");
        logger.info(" redis address:{}:{} ", host, port);

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();


        jedisPoolConfig.setMaxIdle(50);
        jedisPoolConfig.setMinIdle(8);
        jedisPoolConfig.setMaxWaitMillis(10000);
        /** 测试的时候发现会出现'Could not get a resource from the pool'的问题，增加了以下的配置* */
        jedisPoolConfig.setMaxTotal(100);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestOnReturn(true);
        jedisPoolConfig.setTestWhileIdle(true);
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(30000);
        jedisPoolConfig.setNumTestsPerEvictionRun(10);
        jedisPoolConfig.setMinEvictableIdleTimeMillis(60000);
        jedisPoolConfig.setBlockWhenExhausted(false);

//        jedisPoolConfig.setMaxIdle(maxIdle);
//        jedisPoolConfig.setMinIdle(minIdle);
//        jedisPoolConfig.setMaxWaitMillis(10000);
//        jedisPoolConfig.setMaxTotal(100);
//        //当调用borrow Object方法时，是否进行有效性检查
//        jedisPoolConfig.setTestOnBorrow(true);
//        //当调用return Object方法时，是否进行有效性检查
//        jedisPoolConfig.setTestOnReturn(true);
//        jedisPoolConfig.setTestWhileIdle(true);
//        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(30000);
//        jedisPoolConfig.setNumTestsPerEvictionRun(10);
//        jedisPoolConfig.setMinEvictableIdleTimeMillis(60000);
//        jedisPoolConfig.setBlockWhenExhausted(false);

        JedisPool jedisPool = null;
        jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout,password,false);
        return  jedisPool;
    }

}
