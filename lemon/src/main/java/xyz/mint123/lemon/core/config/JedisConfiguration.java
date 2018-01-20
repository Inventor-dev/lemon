package xyz.mint123.lemon.core.config;

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
import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
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

	// redis host
	private String host;
	// redis 端口
	private int port;
	// redis 密码
	private String password;
	// Redis 库
	private int database;
	// redis 超时 时间
	private int timeout;
	// redis 最大链接数
	private int maxTotal;
	// redis 最大等待时间
	private int maxWaitMillist;
	// 最大空闲链接数
	private int maxIdle;
	// 最小空闲链接数
	private int minIdle;
	// 开启事务
	private boolean enableTransaction;

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
