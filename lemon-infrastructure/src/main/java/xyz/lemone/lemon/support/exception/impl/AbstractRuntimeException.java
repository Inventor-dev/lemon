package xyz.lemone.lemon.support.exception.impl;

import xyz.lemone.lemon.support.error.IError;
import xyz.lemone.lemon.support.exception.IException;

/**
 * base of unchecked exceptions.
 * @author lemon
 * @verison 2021/10/03
 */
public class AbstractRuntimeException extends RuntimeException implements IException {

    private static final long serialVersionUID = 8390085952855326498L;

    /**
     * 错误信息.
     */
    protected IError error;

    public AbstractRuntimeException() {
    }

    public AbstractRuntimeException(IError error) {
        super(error.getErrorMessage());
        this.error = error;
    }

    public AbstractRuntimeException(IError error, Throwable cause) {
        super(error.getErrorMessage(), cause);
        this.error = error;
    }

    public AbstractRuntimeException(IError error, Throwable cause, boolean enableSuppression,
                                    boolean writableStackTrace) {
        super(error.getErrorMessage(), cause, enableSuppression, writableStackTrace);
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
