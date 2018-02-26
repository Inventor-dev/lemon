package xyz.mint123.lemon.core.config;

import java.lang.reflect.Method;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import xyz.mint123.lemon.core.Constants;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;

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
			public Object generate(Object o, Method method, Object... objects) {
				StringBuilder sb = new StringBuilder(prefix);
				CacheConfig cacheConfig = o.getClass().getAnnotation(CacheConfig.class);
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
					sb.append(o.getClass().getName()).append(".").append(method.getName());
				}
				sb.append(":");
				if (objects != null) {
					for (Object object : objects) {
						sb.append(JSON.toJSONString(object));
					}
				}
				return sb.toString();
			}
		};
	}


	@Autowired
	private RedisConnectionFactory redisConnectionFactory;

	/**
	 * 实例化 RedisTemplate 对象
	 *
	 * @return
	 */
	@Bean
	public RedisTemplate<String, Object> functionDomainRedisTemplate() {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		initDomainRedisTemplate(redisTemplate, redisConnectionFactory);
		return redisTemplate;
	}

	/**
	 * 设置数据存入 redis 的序列化方式
	 */
	private void initDomainRedisTemplate(RedisTemplate<String, Object> redisTemplate, RedisConnectionFactory factory) {
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashValueSerializer(new GenericFastJsonRedisSerializer());
		redisTemplate.setValueSerializer(new GenericFastJsonRedisSerializer());
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