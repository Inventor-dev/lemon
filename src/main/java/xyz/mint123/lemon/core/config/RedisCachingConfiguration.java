package xyz.mint123.lemon.core.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cache.annotation.*;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import xyz.mint123.lemon.core.util.StringUtils;

import java.lang.reflect.Method;

/**
 * redis 缓存 配置 文件
 *
 * @author lemon
 * @version 2018年1月20日
 */
@Configuration
@ConditionalOnBean(RedisCacheConfiguration.class)
@EnableCaching
public class RedisCachingConfiguration extends CachingConfigurerSupport {

    /**
     * redisTemplate bean ID
     */
    public static final String REDIS_TEMPLATE_BEAN_ID = "redisTemplate";

    private CacheProperties.Redis redisProperties;

    @Autowired
    RedisCachingConfiguration(CacheProperties cacheProperties) {
        this.redisProperties = cacheProperties.getRedis();
    }

    /**
     * 重写key 生成规则
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (Object target, Method method, Object... params) -> {
            StringBuilder sb = new StringBuilder();
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
            if (cacheConfig != null && StringUtils.isEmpty(sb.toString())) {
                String[] cacheNames = cacheConfig.cacheNames();
                if (ArrayUtils.isNotEmpty(cacheNames)) {
                    sb.append(cacheNames[0]);
                }
            }
            if (StringUtils.isEmpty(sb.toString())) {
                sb.append(target.getClass().getName()).append(":").append(method.getName());
            }
            sb.append(":");
            for (Object param : params) {
                sb.append(String.valueOf(param));
            }
            return sb.toString();
        };
    }

    /**
     * redis 缓存配置
     */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        config = config.serializeValuesWith(RedisSerializationContext.SerializationPair
                .fromSerializer(new GenericFastJsonRedisSerializer()));
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
     */
    @Bean(name = RedisCachingConfiguration.REDIS_TEMPLATE_BEAN_ID)
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
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
}