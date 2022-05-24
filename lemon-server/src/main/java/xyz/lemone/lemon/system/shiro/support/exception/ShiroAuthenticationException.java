package xyz.lemone.lemon.system.shiro.support.exception;

import org.apache.shiro.authc.AuthenticationException;
import xyz.lemone.lemon.common.support.error.IError;
import xyz.lemone.lemon.common.support.exception.IException;

/**
 * shiro 认证异常.
 *
 * @author lemon
 */
public class ShiroAuthenticationException extends AuthenticationException implements IException {

    private static final long serialVersionUID = 6790442529468052404L;

    /**
     * 错误信息.
     */
    private IError error;

    public ShiroAuthenticationException(IError error) {
        super(error.getErrorMessage());
        this.error = error;
    }

    public ShiroAuthenticationException(IError error, Throwable cause) {
        super(error.getErrorMessage(), cause);
        this.error = error;
    }

    @Override
    public IError getError() {
        return error;
    }

    public void setError(IError error) {
        this.error = error;
    }
}
