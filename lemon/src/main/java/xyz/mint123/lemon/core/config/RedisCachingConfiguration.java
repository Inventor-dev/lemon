package xyz.mint123.lemon.core.config;

import java.lang.reflect.Method;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.apache.commons.lang3.ArrayUtils;
import xyz.mint123.lemon.core.Constants;
/**
 * redis 缓存 配置 文件
 *
 * @author YP
 * @version 2018年1月20日
 */
@Configuration
@EnableCaching
public class RedisCachingConfiguration extends CachingConfigurerSupport {

    String prefix = Constants.CACHE_NAMESPACE + "M:";

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            /** 重写生成key方法 */
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder(prefix);
                CacheConfig cacheConfig = target.getClass().getAnnotation(CacheConfig.class);
                Cacheable cacheable = method.getAnnotation(Cacheable.class);
                CachePut cachePut = method.getAnnotation(CachePut.class);
                CacheEvict cacheEvict = method.getAnnotation(CacheEvict.class);
                if (cacheable != null) {
                    String[] cacheNames = cacheable.value();
                    if (ArrayUtils.isNotEmpty(cacheNames)) {
                        sb.append(cacheNames[0]);
                    }
                } else if (cachePut != null) {
                    String[] cacheNames = cachePut.value();
                    if (ArrayUtils.isNotEmpty(cacheNames)) {
                        sb.append(cacheNames[0]);
                    }
                } else if (cacheEvict != null) {
                    String[] cacheNames = cacheEvict.value();
                    if (ArrayUtils.isNotEmpty(cacheNames)) {
                        sb.append(cacheNames[0]);
                    }
                }
                if (cacheConfig != null && sb.toString().equals(prefix)) {
                    String[] cacheNames = cacheConfig.cacheNames();
                    if (ArrayUtils.isNotEmpty(cacheNames)) {
                        sb.append(cacheNames[0]);
                    }
                }
                if (sb.toString().equals(prefix)) {
                    sb.append(target.getClass().getName()).append(".").append(method.getName());
                }
                sb.append(":");
                if (params != null) {
                    for (Object object : params) {
                        sb.append(JSON.toJSONString(object));
                    }
                }
                return sb.toString();
            }
        };
    }


    private RedisConnectionFactory redisConnectionFactory;

    private final CacheProperties cacheProperties;

    RedisCachingConfiguration(CacheProperties cacheProperties,RedisConnectionFactory  redisConnectionFactory) {
        this.cacheProperties = cacheProperties;
        this.redisConnectionFactory = redisConnectionFactory;
    }

    /**
     * redis 缓存配置
     * @return
     */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        config = config.serializeValuesWith(RedisSerializationContext.SerializationPair
                .fromSerializer(new GenericFastJsonRedisSerializer()));
        CacheProperties.Redis redisProperties = this.cacheProperties.getRedis();
        if (redisProperties.getTimeToLive() != null) {
            config = config.entryTtl(redisProperties.getTimeToLive());
        }
        if (redisProperties.getKeyPrefix() != null) {
            config = config.prefixKeysWith(redisProperties.getKeyPrefix());
        }
        if (!redisProperties.isCacheNullValues()) {
            config = config.disableCachingNullValues();
        }
        if (!redisProperties.isUseKeyPrefix()) {
            config = config.disableKeyPrefix();
        }
        return config;
    }

    /**
     * 实例化 RedisTemplate 对象
     *
     * @return
     */
    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        initRedisTemplate(redisTemplate, redisConnectionFactory);
        return redisTemplate;
    }

    /**
     * 设置数据存入 redis 的序列化方式
     */
    private void initRedisTemplate(RedisTemplate<String, Object> redisTemplate, RedisConnectionFactory factory) {
        final RedisSerializer keySerializer = new StringRedisSerializer();
        final RedisSerializer valueSerializer = new GenericFastJsonRedisSerializer();
        redisTemplate.setKeySerializer(keySerializer);
        redisTemplate.setHashKeySerializer(keySerializer);
        redisTemplate.setHashValueSerializer(valueSerializer);
        redisTemplate.setValueSerializer(valueSerializer);
        redisTemplate.setConnectionFactory(factory);
    }


    /**
     * 实例化 HashOperations 对象,可以使用 Hash 类型操作
     */
    @Bean
    public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForHash();
    }

    /**
     * 实例化 ValueOperations 对象,可以使用 String 操作
     */
    @Bean
    public ValueOperations<String, Object> valueOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForValue();
    }

    /**
     * 实例化 ListOperations 对象,可以使用 List 操作
     *
     * @return
     */
    @Bean
    public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForList();
    }

    /**
     * 实例化 SetOperations 对象,可以使用 Set 操作
     */
    @Bean
    public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForSet();
    }

    /**
     * 实例化 ZSetOperations 对象,可以使用 ZSet 操作
     */
    @Bean
    public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForZSet();


    }
}