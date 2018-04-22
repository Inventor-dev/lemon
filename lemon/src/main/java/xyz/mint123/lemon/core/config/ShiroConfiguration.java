package xyz.mint123.lemon.core.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import xyz.mint123.lemon.core.support.shiro.AuthorizingRealm;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro 配置
 * @author lemon
 * @version 2018/3/28
 */
@Configuration
public class ShiroConfiguration {

    /**
     * 自定义授权认证类实现
     * @return
     */
    @Bean
    public Realm realm() {
        return new AuthorizingRealm();
    }


    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        chainDefinition.addPathDefinition("/static/**", "anon");
        chainDefinition.addPathDefinition("/druid/**", "anon");
        chainDefinition.addPathDefinition("/loginForm.html", "anon");
        chainDefinition.addPathDefinition("/**", "authc");
        return chainDefinition;
    }
    /**
     * 方法级 AOP 权限检查
     * @return
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        //强制使用 CGlib
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }




}
