package xyz.mint123.lemon.core.support.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.session.mgt.SimpleSession;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import xyz.mint123.lemon.core.util.StringUtils;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Shiro redis 操作
 *
 * @param <K> key
 * @param <V> value
 * @author lemon
 */
public class RedisCache<K, V> implements Cache<K, V> {

    /**
     * 缓存 前缀
     */
    private final String KeyPrefix;

    private final K pattern;

    /**
     * 获取 session 缓存key
     */
    private K getSessionKey(K key) {
        return (K) (KeyPrefix + key);
    }

    private ValueOperations<K, V> valueOperations;

    private RedisTemplate<K, V> redisTemplate;

    public RedisCache(String KeyPrefix, RedisTemplate redisTemplate) {
        this.KeyPrefix = KeyPrefix;
        this.pattern = (K) (KeyPrefix + "*");
        this.redisTemplate = redisTemplate;
        this.valueOperations = redisTemplate.opsForValue();
    }

    @Override
    public V get(K k) throws CacheException {
        if (StringUtils.isEmpty(String.valueOf(k))) {
            return null;
        }
        return valueOperations.get(getSessionKey(k));
    }

    @Override
    public V put(K k, V v) throws CacheException {
        if (StringUtils.isEmpty(String.valueOf(k))) {
            return null;
        }
        valueOperations.set(getSessionKey(k), v);
        redisTemplate.expire(getSessionKey(k), 5000, TimeUnit.SECONDS);
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        if (StringUtils.isEmpty(String.valueOf(k))) {
            return null;
        }
        V v = valueOperations.get(getSessionKey(k));
        redisTemplate.delete(k);
        return v;
    }

    @Override
    public void clear() throws CacheException {
        redisTemplate.delete(redisTemplate.keys(pattern));
    }

    @Override
    public int size() {
        return redisTemplate.keys(pattern).size();
    }

    @Override
    public Set<K> keys() {
        return redisTemplate.keys(pattern);
    }

    @Override
    public Collection<V> values() {
        return valueOperations.multiGet((Set<K>) redisTemplate.keys(pattern));
    }


}

