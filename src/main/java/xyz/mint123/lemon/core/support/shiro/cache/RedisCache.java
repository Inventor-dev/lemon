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

    private static final String CACHE_KEY = "shiro:session";

    private HashOperations<String, K, V> hashOperations;

    public RedisCache(RedisTemplate<String, Object> redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public V get(K k) throws CacheException {
        return hashOperations.get(CACHE_KEY, k);
    }

    @Override
    public V put(K k, V v) throws CacheException {
        hashOperations.put(CACHE_KEY, k, v);
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        hashOperations.delete(CACHE_KEY, k);
        return null;
    }

    @Override
    public void clear() throws CacheException {
        Set<K> keys = hashOperations.keys(CACHE_KEY);
        keys.stream().forEach(k -> hashOperations.delete(CACHE_KEY, k));
    }

    @Override
    public int size() {
        return Math.toIntExact(hashOperations.size(CACHE_KEY));
    }

    @Override
    public Set<K> keys() {
        return hashOperations.keys(CACHE_KEY);
    }

    @Override
    public Collection<V> values() {
        return hashOperations.values(CACHE_KEY);
    }
}

