package xyz.mint123.lemon.support.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import xyz.mint123.lemon.sys.entity.User;

/**
 * 系统授权认证实现类
 * @author lemon
 * @version 2018/3/28
 */

public class AuthorizingRealm extends org.apache.shiro.realm.AuthorizingRealm {


    /**
     * 认证登陆时调用
     * @param autoToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken autoToken){
        UsernamePasswordToken token = (UsernamePasswordToken) autoToken;
        //登陆信息处理
        return new SimpleAuthenticationInfo();
    }

    /**
     * 获取授权信息
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }
}
