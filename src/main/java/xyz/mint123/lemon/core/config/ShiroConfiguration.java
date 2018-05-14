package xyz.mint123.lemon.core.config;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import xyz.mint123.lemon.core.support.shiro.AuthorizingRealm;
import xyz.mint123.lemon.core.support.shiro.cache.RedisCacheManager;
import xyz.mint123.lemon.core.support.shiro.cache.RedisSessionDAO;

/**
 * shiro 配置
 *
 * @author lemon
 * @version 2018/3/28
 */
@Configuration
@ConditionalOnProperty(name = "shiro.enabled", matchIfMissing = true)
public class ShiroConfiguration {


    @Bean("shiroRedisTemplate")
    public RedisTemplate redisTemplate (RedisConnectionFactory redisConnectionFactory  ){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        RedisSerializer keySerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(keySerializer);
        redisTemplate.setHashKeySerializer(keySerializer);
        return redisTemplate;
    }

    /**
     * 自定义授权认证类实现
     *
     * @return
     */
    @Bean
    public Realm realm() {
        return new AuthorizingRealm();
    }


    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
//        静态文件处理
        chainDefinition.addPathDefinition("/css/**", "anon");
        chainDefinition.addPathDefinition("/fonts/**", "anon");
        chainDefinition.addPathDefinition("/img/**", "anon");
        chainDefinition.addPathDefinition("/js/**", "anon");
//        德鲁伊 数据库监控
        chainDefinition.addPathDefinition("/druid/**", "anon");
//        登录表单
        chainDefinition.addPathDefinition("/loginForm.html", "anon");
        chainDefinition.addPathDefinition("/login", "anon");
//        所有路径进行登录验证
        chainDefinition.addPathDefinition("/**", "authc");
        return chainDefinition;
    }

    /**
     * 方法级 AOP 权限检查
     *
     * @return
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        //强制使用 CGlib
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     */
    @Bean
    public RedisSessionDAO redisSessionDAO(@Qualifier("shiroRedisTemplate") RedisTemplate redisTemplate) {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO(new RedisCacheManager(redisTemplate));
        return redisSessionDAO;
    }

    /**
     * Session Manager
     */
    @Bean
    public DefaultWebSessionManager sessionManager(RedisSessionDAO redisSessionDAO) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO);
        return sessionManager;
    }


}
