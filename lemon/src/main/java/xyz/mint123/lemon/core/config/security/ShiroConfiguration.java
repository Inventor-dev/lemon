package xyz.mint123.lemon.core.config.security;

import org.apache.shiro.realm.Realm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.mint123.lemon.support.shiro.AuthorizingRealm;

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


}
