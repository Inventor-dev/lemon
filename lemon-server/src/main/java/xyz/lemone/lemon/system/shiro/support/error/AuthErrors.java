package xyz.lemone.lemon.system.shiro.support.error;

import xyz.lemone.lemon.common.support.error.IError;
import xyz.lemone.lemon.common.support.error.impl.ProcessError;

/**
 * 认证相关错误.
 *
 * @author lemon
 */
public class AuthErrors {

    public static final IError USER_NOT_FOUND = ProcessError
            .of("auth.USER_NOT_FOUND", "用户不存在");

    public static final IError UN_SUPPORT_AUTH_TYPE = ProcessError
            .of("auth.UN_SUPPORT_AUTH_TYPE", "不支持的认证类型");

    private AuthErrors() {
    }

}
