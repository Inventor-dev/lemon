package xyz.lemone.lemon.system.shiro.config;

import com.google.common.collect.Maps;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionStorageEvaluator;
import org.apache.shiro.mgt.SubjectDAO;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SubjectFactory;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.filter.authc.BearerHttpAuthenticationFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSessionStorageEvaluator;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import xyz.lemone.lemon.common.Constants;
import xyz.lemone.lemon.system.shiro.realm.AuthRealm;
import xyz.lemone.lemon.system.shiro.support.mgt.WebSubjectFactory;
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
public class ShiroAutoConfig {

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
    public SubjectFactory subjectFactory() {
        return new WebSubjectFactory();
    }

    @Bean
    public SessionStorageEvaluator sessionStorageEvaluator() {
        DefaultWebSessionStorageEvaluator evaluator = new DefaultWebSessionStorageEvaluator();
        // 禁用session
        evaluator.setSessionStorageEnabled(Boolean.FALSE);
        return evaluator;
    }

    @Bean
    public SubjectDAO subjectDao(SessionStorageEvaluator sessionStorageEvaluator) {
        DefaultSubjectDAO subjectDao = new DefaultSubjectDAO();
        subjectDao.setSessionStorageEvaluator(sessionStorageEvaluator);
        return subjectDao;
    }

    @Bean
    public SessionManager sessionManager() {
        DefaultSessionManager shiroSessionManager = new DefaultSessionManager();
        // 关闭session校验轮询
        shiroSessionManager.setSessionValidationSchedulerEnabled(Boolean.FALSE);
        return shiroSessionManager;
    }

    @Bean
    public SecurityManager securityManager(
            List<Realm> realms, Authorizer authorizer, Authenticator authenticator,
            SubjectDAO subjectdao, SubjectFactory subjectFactory, SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setAuthenticator(authenticator);
        securityManager.setAuthorizer(authorizer);
        securityManager.setSubjectDAO(subjectdao);
        securityManager.setSubjectFactory(subjectFactory);
        securityManager.setSessionManager(sessionManager);
        securityManager.setRealms(realms);
        return securityManager;
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
