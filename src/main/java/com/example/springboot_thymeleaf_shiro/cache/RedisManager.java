package com.example.springboot_thymeleaf_shiro.cache;


import com.example.springboot_thymeleaf_shiro.utils.ApplicationContextUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Collection;
import java.util.Set;

public class RedisManager<K,V> implements Cache<K,V> {

    private String cacheName;

    public RedisManager() {
    }

    public RedisManager(String cacheName) {
        this.cacheName = cacheName;
    }

    @Override
    public V get(K key) throws CacheException {
        return (V)getRedisTemplate().opsForHash().get(this.cacheName,key.toString());
    }

    @Override
    public V put(K key, V value) throws CacheException {
        System.out.println("put key"+key);
        System.out.println("put value"+value);
        getRedisTemplate().opsForHash().put(this.cacheName,key.toString(),value);
        return null;
    }

    @Override
    public V remove(K key) throws CacheException {
        return (V) getRedisTemplate().opsForHash().delete(this.cacheName,key.toString());
    }

    @Override
    public void clear() throws CacheException {
        getRedisTemplate().opsForHash().delete(this.cacheName);
    }

    @Override
    public int size() {
        return getRedisTemplate().opsForHash().size(this.cacheName).intValue();
    }

    @Override
    public Set<K> keys() {
        return getRedisTemplate().opsForHash().keys(this.cacheName);
    }

    @Override
    public Collection<V> values() {
        return getRedisTemplate().opsForHash().values(this.cacheName);
    }

    private RedisTemplate getRedisTemplate(){
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
