package com.eth.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

@SuppressWarnings(value = {"unchecked", "rawtypes"})
@Component
public class RedisCache {
    @Autowired
    public RedisTemplate redisTemplate;


    public <T> void setCacheObject(final String key, final T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key      Redis键
     * @param value    Redis值
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     * @return
     */
    public <T> void setCacheObject(final String key, final T value, final Integer timeout, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    public boolean expire(final String key, final long timeout) {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置有效时间
     *
     * @param key     Redus键
     * @param timeout 超时时间
     * @param unit    时间单位
     * @return true=设置成功,false=设置失败
     */
    public boolean expire(final String key, final long timeout, final TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    public <T> T getCacheObject(final String key) {
        ValueOperations<String, T> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    /**
     * 删除单个对象
     *
     * @param
     * @return
     */
    public boolean deleteObject(final String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 删除集合对象
     *
     * @param
     * @return
     */
    public long deleteObject(final Collection collection) {
        return redisTemplate.delete(collection);

    }

    /**
     * 缓存List数据
     *
     * @param
     * @return
     */
    public <T> long setCacheList(final String key, final List<T> dataList) {
        Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
        return count == null ? 0 : count;
    }

    /**
     * 获得缓存的list对象
     *
     * @param
     * @return
     */
    public <T> List<T> getCacheList(final String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * 缓存Set
     *
     * @param
     * @return
     */
    public <T> BoundSetOperations<String, T> setCacheSet(final String key, final Set<T> dadaSet) {
        BoundSetOperations<String, T> setOperations = redisTemplate.boundSetOps(key);
        Iterator<T> it = dadaSet.iterator();
        while (it.hasNext()) {
            setOperations.add(it.next());
        }
        return setOperations;
    }

    /**
     * 获得缓存的set
     *
     * @param
     * @return
     */
    public <T> Set<T> getCacheSet(final String key){
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 缓存map
     *
     * @param
     * @return
     */
    public <T> void setCacheMap(final String key , final Map<String,T> dataMap){
        if (dataMap != null) {
            redisTemplate.opsForHash().putAll(key,dataMap);
        }
    }

    /**
     * 获得缓存的Map
     *
     * @param
     * @return
     */
    public <T> Map<String,T>getCachemap(final String key){
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 往Hash中存入数据
     *
     * @param
     * @return
     */
    public <T> void setCacheMapValue(final String key, final String hkey,final T value){
        redisTemplate.opsForHash().put(key,hkey,value);
    }

    /**
     * 获取Hash中的数据
     *
     * @param
     * @return
     */
    public <T> T getCacheMapValue(final String key, final String hkey){
        HashOperations<String,String,T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key,hkey);
    }

    /**
     * 删除Hash中的数据
     *
     * @param
     * @return
     */
    public void delCacheMapValue(final String key,final String hkey){
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.delete(key,hkey);
    }

    /**
     * 获取多个Hash中的数据
     *
     * @param
     * @return
     */
    public <T> List<T> getMultiCacheMapValue(final String key,final Collection<Object>hkeys){
        return redisTemplate.opsForHash().multiGet(key,hkeys);
    }

    /**
     * 获得缓存的基本对象列表
     *
     * @param
     * @return
     */
    public Collection<String> keys(final String pattern){
        return redisTemplate.keys(pattern);
    }
}



