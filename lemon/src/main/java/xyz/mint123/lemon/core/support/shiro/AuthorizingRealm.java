package xyz.mint123.lemon.core.support.shiro;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.mint123.lemon.sys.entity.User;
import xyz.mint123.lemon.sys.service.UserService;
import xyz.mint123.lemon.sys.util.UserUtils;

/**
 * 系统授权认证实现类
 * @author lemon
 * @version 2018/3/28
 */
public class AuthorizingRealm extends org.apache.shiro.realm.AuthorizingRealm {

    /**
     * 认证登陆时调用
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException{
        UsernamePasswordToken  usernamePasswordToken = (UsernamePasswordToken) token;
        User user = UserUtils.getUserByLoginName(usernamePasswordToken.getUsername().trim().toUpperCase());
        if (user == null){
            throw new AuthenticationException("用户不存在！");
        }
        //登陆信息处理
        return new SimpleAuthenticationInfo(user,user.getPassword(),getName());
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
