package xyz.lemone.lemon.system.shiro.realm;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.BearerToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;
import xyz.lemone.lemon.common.support.auth.UserInfo;
import xyz.lemone.lemon.system.shiro.support.error.AuthErrors;
import xyz.lemone.lemon.system.shiro.support.exception.ShiroAuthenticationException;
import xyz.lemone.lemon.system.shiro.support.toolkit.ShiroUtils;
import xyz.lemone.lemon.system.user.entity.SysUser;
import xyz.lemone.lemon.system.user.service.SysUserService;

import java.util.function.Supplier;

/**
 * Common Auth Realm.
 *
 * <p>shiro 多 realm 时异常明细无法抛出至前台
 * 故 单 realm 处理所有逻辑
 *
 * @author lemon
 * @version 2021/10/03
 */
public class AuthRealm extends AuthenticatingRealm {

    final Supplier<ShiroAuthenticationException> userNotFoundExceptionSupplier = ()
            -> new ShiroAuthenticationException(AuthErrors.USER_NOT_FOUND);

    final ShiroAuthenticationException unSupportAuthTypeException =
            new ShiroAuthenticationException(AuthErrors.UN_SUPPORT_AUTH_TYPE);

    private SysUserService sysUserService;

    @Override
    public boolean supports(AuthenticationToken token) {
        // support auth types
        return token instanceof BearerToken
                || token instanceof UsernamePasswordToken;

    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authToken) throws AuthenticationException {

        if (authToken instanceof UsernamePasswordToken) {
            return getUsernamePasswordAuthenticationInfo((UsernamePasswordToken) authToken);
        }

        throw unSupportAuthTypeException;
    }

    /**
     * 用户名密模式.
     *
     * @param authToken auth info
     * @return
     */
    private AuthenticationInfo getUsernamePasswordAuthenticationInfo(UsernamePasswordToken authToken) {
        SysUser sysUser = sysUserService.loadByUsername(authToken.getUsername())
                .orElseThrow(userNotFoundExceptionSupplier);
        String[] split = ShiroUtils.getSaltPassword(sysUser.getPassword());
        return new SimpleAuthenticationInfo(sysUserToPrincipal(sysUser),
                split[1], ByteSource.Util.bytes(split[0]), getName());
    }

    /**
     * SysUser To Principal.
     *
     * @param sysUser user
     * @return
     */
    private UserInfo sysUserToPrincipal(SysUser sysUser) {
        return UserInfo.UserInfoBuilder.anUserInfo()
                .withUserId(sysUser.getUserId())
                .withUsername(sysUser.getUsername())
                .withPhone(sysUser.getPhone())
                .withUserCode(sysUser.getUserCode())
                .withRealName(sysUser.getRealName())
                .build();
    }

    public SysUserService getSysUserService() {
        return sysUserService;
    }

    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }
}
