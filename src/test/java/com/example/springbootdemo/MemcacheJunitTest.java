package com.example.springbootdemo;

import com.danga.MemCached.MemCachedClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author BG362793
 * memcached 缓存 单元测试
 */
public class MemcacheJunitTest extends SpringbootdemoApplicationTests{

    @Autowired
    private MemCachedClient memCachedClient;

    @Test
    public void testMemcached(){

        //放入缓存
        boolean flag = memCachedClient.set("haha",1212);

        Object a = memCachedClient.get("haha");
        System.out.println(a);

    }
}
