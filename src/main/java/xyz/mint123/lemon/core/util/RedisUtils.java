package xyz.mint123.lemon.core.util;

import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.core.*;
import xyz.mint123.lemon.core.config.RedisCachingConfiguration;

import java.util.concurrent.TimeUnit;

/**
 * redis 缓存工具类
 *
 * @author lemon
 * @version 2018/2/24
 */
public class RedisUtils {

    /**
     * redis 缓存前缀
     */
    public static final String REDIS_KEY_PREFIX;

    /**
     * redis 操作模板
     */
    private static RedisTemplate<String, Object> redisTemplate;
    /**
     * 值操作
     */
    private static ValueOperations<String, Object> valueOperations;

    static {
        CacheProperties cacheProperties = SpringUtils.getBean(CacheProperties.class);
        REDIS_KEY_PREFIX = cacheProperties.getRedis().getKeyPrefix();
        redisTemplate = SpringUtils.getBean(RedisCachingConfiguration.REDIS_TEMPLATE_BEAN_ID, RedisTemplate.class);
        valueOperations = redisTemplate.opsForValue();
    }


    private static String getRedisKey(String key) {
        return REDIS_KEY_PREFIX + key;
    }


    /**
     * 判断key 是否存在
     *
     * @param key cacheKey
     * @return
     */
    public static boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }


    /**
     * 获取 指定 key的值
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> T get(String key) {
        ValueOperations<String, T> thatValueOperations = (ValueOperations<String, T>) valueOperations;
        return thatValueOperations.get(key);
    }

    /**
     * 存入指定数据
     *
     * @param key   key
     * @param value 值
     */
    public <T> void set(String key, T value) {
        valueOperations.set(key, value);
    }

    /**
     * 存入指定数据
     *
     * @param key    key
     * @param value  值
     * @param expire 过期时间(单位:秒)
     */
    public <T> void set(String key, T value, long expire) {
        valueOperations.set(key, value, expire, TimeUnit.SECONDS);
    }

    /**
     * 获取值操作
     *
     * @return
     */
    public static ValueOperations<String, Object> getValueOperations() {
        return valueOperations;
    }

    /**
     * 获取 redis 模板
     *
     * @return
     */
    public static RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    /**
     * hash 操作
     */
    public static class Hash {

        private static HashOperations<String, String, Object> hashOperations;

        static {
            hashOperations = redisTemplate.opsForHash();
        }

        /**
         * 添加
         *
         * @param key     mapkey
         * @param hashKey 单条记录key
         * @param value   值
         * @param expire  过期时间(单位:秒),传入 -1 时表示不设置过期时间
         */
        public static <T> void put(String key, String hashKey, T value, long expire) {
            String cacheKey = getRedisKey(key);
            hashOperations.put(cacheKey, hashKey, value);
            if (expire != -1) {
                redisTemplate.expire(cacheKey, expire, TimeUnit.SECONDS);
            }
        }

        /**
         * 删除
         *
         * @param key     redis key
         * @param hashKey 单条记录key
         */
        public static void remove(String key, String hashKey) {
            hashOperations.delete(getRedisKey(key), hashKey);
        }

        /**
         * 获取单条
         *
         * @param key     redis key
         * @param hashKey 单条key
         */
        public static <T> T get(String key, String hashKey) {
            HashOperations<String, String, T> thatHashOperations = (HashOperations<String, String, T>) hashOperations;
            return thatHashOperations.get(getRedisKey(key), hashKey);
        }

        /**
         * 获取 指定key 下数据
         *
         * @param key
         * @param <T>
         */
        public static <T> java.util.List<T> getAll(String key) {
            HashOperations<String, String, T> thatHashOperations = (HashOperations<String, String, T>) hashOperations;
            return thatHashOperations.values(getRedisKey(key));
        }

        /**
         * 获取 指定key 下所有 hashKey
         */
        public static java.util.Set<String> getKeys(String key) {
            return hashOperations.keys(getRedisKey(key));
        }

        /**
         * 判断key 是否存在
         *
         * @param key
         * @param hashKey
         */
        public static boolean isKeyExists(String key, String hashKey) {
            return hashOperations.hasKey(getRedisKey(key), hashKey);
        }

        /**
         * 查询指定key下缓存数量
         */
        public static long count(String key) {
            return hashOperations.size(getRedisKey(key));
        }

        /**
         * 清空 指定 key
         *
         * @param key
         */
        public static void empty(String key) {
            final String cacheKey = getRedisKey(key);
            java.util.Set<String> set = hashOperations.keys(cacheKey);
            set.stream().forEach(hashKey -> hashOperations.delete(cacheKey, hashKey));
        }

        /**
         * 获取 hash 操作
         */
        public static HashOperations<String, String, Object> getHashOperations() {
            return hashOperations;
        }
    }

    /**
     * list 操作
     */
    public static class List {

        private static ListOperations<String, Object> stringObjectListOperations;

        static {
            stringObjectListOperations = redisTemplate.opsForList();
        }

        public static ListOperations<String, Object> getStringObjectListOperations() {
            return stringObjectListOperations;
        }
    }

    /**
     * set 操作
     */
    public static class Set {

        private static SetOperations<String, Object> setOperations;

        static {
            setOperations = redisTemplate.opsForSet();
        }

        public static SetOperations<String, Object> getSetOperations() {
            return setOperations;
        }
    }

    /**
     * zset  操作
     */
    public static class Zset {

        private static ZSetOperations<String, Object> zSetOperations;

        static {
            zSetOperations = redisTemplate.opsForZSet();
        }

        public static ZSetOperations<String, Object> getzSetOperations() {
            return zSetOperations;
        }
    }
}
