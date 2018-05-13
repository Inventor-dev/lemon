package xyz.mint123.lemon.core.support.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.Set;

/**
 * Shiro redis 操作
 *
 * @param <K> key
 * @param <V> value
 * @author lemon
 */
public class RedisCache<K, V> implements Cache<K, V> {

    private final String cacheKey;

    private HashOperations<String, K, V> hashOperations;

    public RedisCache(String cacheKey,RedisTemplate<String, Object> redisTemplate) {
        this.cacheKey = cacheKey;
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public V get(K k) throws CacheException {
        return hashOperations.get(cacheKey, k);
    }

    @Override
    public V put(K k, V v) throws CacheException {
        hashOperations.put(cacheKey, k, v);
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        hashOperations.delete(cacheKey, k);
        return null;
    }

    @Override
    public void clear() throws CacheException {
        Set<K> keys = hashOperations.keys(cacheKey);
        keys.forEach(k -> hashOperations.delete(cacheKey, k));
    }

    @Override
    public int size() {
        return Integer.valueOf(String.valueOf(hashOperations.size(cacheKey)));
    }

    @Override
    public Set<K> keys() {
        return hashOperations.keys(cacheKey);
    }

    @Override
    public Collection<V> values() {
        return hashOperations.values(cacheKey);
    }


}

