package xyz.mint123.lemon.core.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import xyz.mint123.lemon.core.Constants;
import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis 缓存工具类
 * @author lemon
 * @version 2018/2/24
 */
@Component
public class RedisUtils {

    @Autowired
    protected RedisTemplate<String, Object> redisTemplate;

    @Resource
    protected  HashOperations<String, String, Object> hashOperations;

    @Autowired
    protected CacheProperties cacheProperties;
    /**
     * 缓存 前缀
     * @return
     */
    protected String getRedisKey(){
        return cacheProperties.getRedis().getKeyPrefix();
    }

    /**
     * 添加
     *
     * @param key    key
     * @param doamin 对象
     * @param expire 过期时间(单位:秒),传入 -1 时表示不设置过期时间
     */
    public <T> void put(String key, T doamin, long expire) {
        hashOperations.put(getRedisKey(), key, doamin);
        if (expire != -1) {
            redisTemplate.expire(getRedisKey(), expire, TimeUnit.SECONDS);
        }
    }

    /**
     * 删除
     *
     * @param key 传入key的名称
     */
    public void remove(String key) {
        hashOperations.delete(getRedisKey(), key);
    }

    /**
     * 查询
     *
     * @param key 查询的key
     * @return
     */
    public <T> T get(String key) {
        HashOperations<String, String, T> thatHashOperations = (HashOperations<String, String, T>) hashOperations;
        return thatHashOperations.get(getRedisKey(), key);
    }

    /**
     * 获取当前redis库下所有对象
     * @return
     */
    public <T> List<T> getAll() {
        HashOperations<String, String, T> thatHashOperations = (HashOperations<String, String, T>) hashOperations;
        return thatHashOperations.values(getRedisKey());
    }

    /**
     * 查询查询当前redis库下所有key
     *
     * @return
     */
    public Set<String> getKeys() {
        return hashOperations.keys(getRedisKey());
    }

    /**
     * 判断key是否存在redis中
     *
     * @param key 传入key的名称
     * @return
     */
    public boolean isKeyExists(String key) {
        return hashOperations.hasKey(getRedisKey(), key);
    }

    /**
     * 查询当前key下缓存数量
     *
     * @return
     */
    public long count() {
        return hashOperations.size(getRedisKey());
    }

    /**
     * 清空redis
     */
    public void empty() {
        Set<String> set = hashOperations.keys(getRedisKey());
        set.stream().forEach(key -> hashOperations.delete(getRedisKey(), key));
    }


}
