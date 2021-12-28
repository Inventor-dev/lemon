package xyz.lemone.lemon.system.shiro.config;

import com.google.common.collect.Maps;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionStorageEvaluator;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.mgt.SubjectDAO;
import org.apache.shiro.mgt.SubjectFactory;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.AbstractShiroWebConfiguration;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.filter.authc.BearerHttpAuthenticationFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.apache.shiro.web.mgt.DefaultWebSessionStorageEvaluator;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import xyz.lemone.lemon.common.Constants;
import xyz.lemone.lemon.system.shiro.realm.AuthRealm;
import xyz.lemone.lemon.system.shiro.support.toolkit.ShiroUtils;
import xyz.lemone.lemon.system.user.service.SysUserService;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.List;

/**
 * shiro 配置.
 *
 * @author lemon
 */
@Configuration
public class ShiroAutoConfig extends AbstractShiroWebConfiguration {

    @Value("#{ @environment['shiro.loginUrl'] ?: '/a/login' }")
    protected String loginUrl;

    @Value("#{ @environment['shiro.successUrl'] ?: '/a/' }")
    protected String successUrl;

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(Boolean.TRUE);
        return proxyCreator;
    }

    @Bean
    @Override
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        chainDefinition.addPathDefinition(Constants.API_PREFIX + "/**", DefaultFilter.authcBearer.name());
        return chainDefinition;
    }

    @Bean
    public AuthRealm authRealm(SysUserService sysUserService) {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName(ShiroUtils.HASH_ALGORITHM_NAME);
        matcher.setHashIterations(ShiroUtils.HASH_ITERATIONS);
        AuthRealm authRealm = new AuthRealm();
        authRealm.setCredentialsMatcher(matcher);
        authRealm.setSysUserService(sysUserService);
        return authRealm;
    }

    @Bean
    @Override
    public SubjectFactory subjectFactory() {
        return super.subjectFactory();
    }

    @Bean
    @Override
    public SessionStorageEvaluator sessionStorageEvaluator() {
        DefaultWebSessionStorageEvaluator evaluator = new DefaultWebSessionStorageEvaluator();
        // 禁用session
        evaluator.setSessionStorageEnabled(Boolean.FALSE);
        return evaluator;
    }

    @Bean
    @Override
    public SubjectDAO subjectDAO() {
        return super.subjectDAO();
    }

    @Bean
    @Override
    public SessionManager sessionManager() {
        SessionManager sessionManager = super.sessionManager();
        if (sessionManager instanceof DefaultSessionManager) {
            DefaultSessionManager shiroSessionManager = (DefaultSessionManager) sessionManager;
            // 关闭session校验轮询
            shiroSessionManager.setSessionValidationSchedulerEnabled(Boolean.FALSE);
        }
        return sessionManager;
    }

    @Bean
    @Override
    public Authenticator authenticator() {
        return super.authenticator();
    }

    @Bean
    @Override
    public Authorizer authorizer() {
        return super.authorizer();
    }

    @Override
    @Bean
    public SessionsSecurityManager securityManager(List<Realm> realms) {
        return super.securityManager(realms);
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(
            SecurityManager securityManager, @Qualifier("globalFilters") List<String> globalFilters,
            ShiroFilterChainDefinition shiroFilterChainDefinition) {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setLoginUrl(loginUrl);
        filterFactoryBean.setSuccessUrl(successUrl);
        filterFactoryBean.setSecurityManager(securityManager);
        filterFactoryBean.setGlobalFilters(globalFilters);
        filterFactoryBean.setFilterChainDefinitionMap(shiroFilterChainDefinition.getFilterChainMap());
        HashMap<String, Filter> filters = Maps.newHashMapWithExpectedSize(2);
        filters.put(DefaultFilter.authcBearer.name(), new BearerHttpAuthenticationFilter());
        filterFactoryBean.setFilters(filters);
        return filterFactoryBean;
    }

}
