package com.example.springbootdemo.memcachedConfig;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author BG362793
 * memcached 缓存的配置信息
 */

@Component
@EnableCaching
public class MemcachedConfig {

    private Logger logger = LoggerFactory.getLogger(MemcachedConfig.class);

    @Value("${memcache.servers}")
    private String[] servers;

    @Value("${memcache.weights}")
    private Integer[] weights;

    @Value("${memcache.initConn}")
    private int initConn;

    @Value("${memcache.minConn}")
    private int minConn;

    @Value("${memcache.maxConn}")
    private int maxConn;

    @Value("${memcache.maintSleep}")
    private long maintSleep;

    @Value("${memcache.nagle}")
    private Boolean nagle;

    @Value("${memcache.socketTO}")
    private int socketTo;


    @Bean
    public SockIOPool sockIOPool(){
        //获取连接池的实例
        SockIOPool pool = SockIOPool.getInstance();
        //服务器列表及其权重
        //设置服务器信息
        pool.setServers(servers);
        pool.setWeights(weights);
        //设置初始连接数、最小连接数、最大连接数、最大处理时间
        pool.setInitConn(initConn);
        pool.setMinConn(minConn);
        pool.setMaxConn(maxConn);
        //设置连接池守护线程的睡眠时间
        pool.setMaintSleep(maintSleep);
        //设置TCP参数，连接超时
        pool.setNagle(nagle);
        pool.setSocketConnectTO(socketTo);
        //初始化并启动连接池
        pool.initialize();
        return pool;
    }

    @Bean
    public MemCachedClient returnMemcachedClient(){
        return new MemCachedClient();
    }

}
