package com.example.gradle.gradledemo.configs.shiro.redis;

import cn.hutool.core.util.ObjectUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;

public class ShiroRedisCache<K, V> implements Cache<K, V> {
    private RedisTemplate<Object, Object> redisTemplate;

    public String getPrefix() {
        String prefix = "shiro_redis";
        return prefix + ":";
    }

    public ShiroRedisCache(RedisTemplate<Object, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @Override
    public V get(K k) throws CacheException {
        System.out.println("获取=====================" + k);
        if (k == null) {
            return null;
        }
        byte[] bytes = getBytesKey(k);
        return (V)redisTemplate.opsForValue().get(bytes);
    }

    @Override
    public V put(K k, V v) throws CacheException {
        System.out.println("设置=====================" + k + "==:==" + v);
        if (k== null || v == null) {
            return null;
        }
        byte[] bytes = getBytesKey(k);
        redisTemplate.opsForValue().set(bytes, v);
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        System.out.println("删除=====================" + k);
        if(k == null){
            return null;
        }
        byte[] bytes =getBytesKey(k);
        V v = (V)redisTemplate.opsForValue().get(bytes);
        redisTemplate.delete(bytes);
        return v;
    }

    @Override
    public void clear() throws CacheException {
        Objects.requireNonNull(redisTemplate.getConnectionFactory()).getConnection().flushDb();
    }

    @Override
    public int size() {
        return Objects.requireNonNull(Objects.requireNonNull(redisTemplate.getConnectionFactory()).getConnection().dbSize()).intValue();
    }

    @Override
    public Set<K> keys() {
        byte[] bytes = (getPrefix() + "*").getBytes();
        Set<Object> keys = redisTemplate.keys(bytes);
        Set<K> sets = new HashSet<>();
        for (Object key: Objects.requireNonNull(keys)) {
            sets.add((K)key);
        }
        return sets;
    }

    @Override
    public Collection<V> values() {
        Set<K> keys = keys();
        List<V> values = new ArrayList<>(keys.size());
        for(K k :keys){
            values.add(get(k));
        }
        return values;
    }

    private byte[] getBytesKey(K key){
        if(key instanceof String){
            String preKey = this.getPrefix() + key;
            return preKey.getBytes();
        } else {
            return ObjectUtil.serialize(key);
        }
    }
}
