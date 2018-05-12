package xyz.mint123.lemon.core.config;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.core.RedisTemplate;
import xyz.mint123.lemon.core.support.shiro.AuthorizingRealm;
import xyz.mint123.lemon.core.support.shiro.cache.RedisCache;
import xyz.mint123.lemon.core.support.shiro.cache.RedisCacheManager;
import xyz.mint123.lemon.core.support.shiro.cache.RedisSessionDAO;
import xyz.mint123.lemon.core.support.shiro.cache.ShiroWebSessionManager;

/**
 * shiro 配置
 *
 * @author lemon
 * @version 2018/3/28
 */
@Configuration
@ConditionalOnProperty(name = "shiro.enabled", matchIfMissing = true)
public class ShiroConfiguration {

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
     * session cache
     */
    @Bean
    public Cache cache(RedisTemplate redisTemplate) {
        return new RedisCache(redisTemplate);
    }

    @Bean
    public CacheManager cacheManager(Cache cache) {
        return new RedisCacheManager(cache);
    }
    /**
     * session dao
     *
     * @return
     */
    @Bean
    public SessionDAO sessionDAO(CacheManager cacheManager, RedisTemplate redisTemplate) {
        return new RedisSessionDAO( cacheManager,redisTemplate);
    }

    @Bean
    public SessionManager sessionManager(SessionDAO sessionDAO,CacheManager cacheManager) {
        ShiroWebSessionManager shiroWebSessionManager = new ShiroWebSessionManager();
        shiroWebSessionManager.setSessionDAO(sessionDAO);
        shiroWebSessionManager.setCacheManager(cacheManager);
        return shiroWebSessionManager;
    }

}
