package xyz.mint123.lemon.core.config;

import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import xyz.mint123.lemon.core.support.shiro.AuthorizingRealm;

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


    /**
     * 保证实现了Shiro内部lifecycle函数的bean执行
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
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

    /**
     * 注解支持
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SessionsSecurityManager sessionsSecurityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(sessionsSecurityManager);
        return authorizationAttributeSourceAdvisor;
    }




}
