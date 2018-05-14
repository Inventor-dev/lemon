package xyz.mint123.lemon.core.support.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import xyz.mint123.lemon.core.util.StringUtils;

/**
 * shiro redis 缓存管理器
 */
public class RedisCacheManager implements CacheManager {

    /**
     * 默认 session 缓存 前缀
     */
    public static final String DEFULT_SESSION_KEY_PREFIX = "shiro:session:";

    private RedisTemplate<Object, Object> redisTemplate;

    public RedisCacheManager(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String KeyPrefix) throws CacheException {
        return new RedisCache<K, V>(DEFULT_SESSION_KEY_PREFIX, redisTemplate);
    }


}
