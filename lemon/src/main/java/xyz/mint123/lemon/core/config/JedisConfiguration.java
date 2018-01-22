package xyz.mint123.lemon.core.config;

import java.lang.reflect.Method;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration.JedisClientConfigurationBuilder;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;

import xyz.mint123.lemon.core.Constants;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * redis 配置 文件
 * 
 * @author YP
 * @version 2018年1月20日
 */
@Configuration
@EnableCaching
@ConfigurationProperties(prefix = "redis")
public class JedisConfiguration extends CachingConfigurerSupport {
	
	String  prefix = Constants.CACHE_NAMESPACE + "M:";

    /**
     *  redis host
     */
	private String host;
    /**
     * redis 端口
     */
	private int port;
    /**
     * redis 密码
     */
	private String password;
    /**
     * Redis 库
     */
	private int database;
    /**
     * redis 超时 时间
     */
	private int timeout;
    /**
     * redis 最大链接数
     */
	private int maxTotal;
    /**
     * redis 最大等待时间
     */
	private int maxWaitMillist;
    /**
     * 最大空闲链接数
     */
	private int maxIdle;
    /**
     *  最小空闲链接数
     */
	private int minIdle;
    /**
     * 开启事务
     */
	private boolean enableTransaction;


	@Bean @Override
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

	@Bean
	public JedisClientConfiguration jedisClientConfiguration() {
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		poolConfig.setMinIdle(minIdle);
		poolConfig.setMaxIdle(maxIdle);
		poolConfig.setMaxTotal(maxTotal);
		poolConfig.setMaxWaitMillis(maxWaitMillist);
		JedisClientConfigurationBuilder builder = JedisClientConfiguration.builder();
		builder.usePooling().poolConfig(poolConfig);
		return builder.build();
	}

	@Bean
	public JedisConnectionFactory jedisConnectionFactory(@Autowired JedisClientConfiguration jedisClientConfiguration) {
		RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
		redisConfig.setHostName(host);
		redisConfig.setPort(port);
		redisConfig.setPassword(RedisPassword.of(password));
		redisConfig.setDatabase(database);
		return new JedisConnectionFactory(redisConfig, jedisClientConfiguration);
	}

	@Bean
	public CacheManager cacheManager(JedisConnectionFactory jedisConnectionFactory) {
		RedisCacheWriter cacheWriter = RedisCacheWriter.lockingRedisCacheWriter(jedisConnectionFactory);
		RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig();
		cacheConfig.serializeValuesWith(SerializationPair.fromSerializer(new GenericFastJsonRedisSerializer()));
		RedisCacheManager cacheManager = new RedisCacheManager(cacheWriter, cacheConfig);
		cacheManager.setTransactionAware(true);
		return cacheManager;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getDatabase() {
		return database;
	}

	public void setDatabase(int database) {
		this.database = database;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getMaxTotal() {
		return maxTotal;
	}

	public void setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
	}

	public int getMaxWaitMillist() {
		return maxWaitMillist;
	}

	public void setMaxWaitMillist(int maxWaitMillist) {
		this.maxWaitMillist = maxWaitMillist;
	}

	public int getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	public int getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}

	public boolean isEnableTransaction() {
		return enableTransaction;
	}

	public void setEnableTransaction(boolean enableTransaction) {
		this.enableTransaction = enableTransaction;
	}

}
