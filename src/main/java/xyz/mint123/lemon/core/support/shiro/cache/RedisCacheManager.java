package xyz.mint123.lemon.core.support.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * shiro redis 缓存管理器
 */
public class RedisCacheManager implements CacheManager {

    private Cache cache;

    public RedisCacheManager(Cache cache) {
         this.cache = cache;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return cache;
    }
}
