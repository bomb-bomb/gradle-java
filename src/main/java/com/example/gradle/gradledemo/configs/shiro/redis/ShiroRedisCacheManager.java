package com.example.gradle.gradledemo.configs.shiro.redis;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;

public class ShiroRedisCacheManager implements CacheManager {

    private RedisTemplate<Object, Object> redisTemplate;

    public ShiroRedisCacheManager(RedisTemplate<Object, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        System.out.println("getCache=============================");
        return new ShiroRedisCache(redisTemplate);
    }
}
