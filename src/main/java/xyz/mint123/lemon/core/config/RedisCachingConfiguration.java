package xyz.mint123.lemon.core.config;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.cache.annotation.*;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import xyz.mint123.lemon.core.util.StringUtils;

import java.lang.reflect.Method;

/**
 * redis 缓存 配置 文件
 *
 * @author lemon
 * @version 2018年1月20日
 */
@Configuration
@EnableCaching
public class RedisCachingConfiguration extends CachingConfigurerSupport {

    /**
     * redisTemplate bean ID
     */
    public static final String REDIS_TEMPLATE_BEAN_ID = "redisTemplate";

    public static final String REACTIVE_REDIS_TEMPLATE_BEAN_ID = "reactiveRedisTemplate";

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


//    @Bean(REDIS_TEMPLATE_BEAN_ID)
//    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        GenericJackson2JsonRedisSerializer valueSerializer = new GenericJackson2JsonRedisSerializer();
//        RedisTemplate<Object, Object> template = new RedisTemplate<>();
//        RedisSerializer<String> keySerializer = template.getStringSerializer();
//        template.setConnectionFactory(redisConnectionFactory);
//        template.setDefaultSerializer(valueSerializer);
//        template.setKeySerializer(keySerializer);
//        template.setHashValueSerializer(keySerializer);
//        template.setValueSerializer(valueSerializer);
//        template.setHashValueSerializer(valueSerializer);
//        return template;
//    }


    @Bean(REACTIVE_REDIS_TEMPLATE_BEAN_ID)
    public ReactiveRedisTemplate<Object, Object> reactiveRedisTemplate(ResourceLoader resourceLoader,
                                                                       ReactiveRedisConnectionFactory reactiveRedisConnectionFactory) {
        ReactiveRedisTemplate<Object, Object> reactiveRedisTemplate = new ReactiveRedisTemplate<>(reactiveRedisConnectionFactory,
                RedisSerializationContext.fromSerializer(new GenericJackson2JsonRedisSerializer()));
        return reactiveRedisTemplate;
    }


}