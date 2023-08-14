package xyz.lemone.lemon.support.exception.impl;

import xyz.lemone.lemon.support.error.IError;
import xyz.lemone.lemon.support.exception.impl.AbstractRuntimeException;

/**
 * 业务处理失败异常.
 *
 * @author lemon
 */
public abstract class BaseProcessFailedException extends AbstractRuntimeException {

    private static final long serialVersionUID = -1291449181632984701L;

    public BaseProcessFailedException() {
    }

    public BaseProcessFailedException(IError error) {
        super(error);
    }

    public BaseProcessFailedException(IError error, Throwable cause) {
        super(error, cause);
    }

    public BaseProcessFailedException(IError error, Throwable cause, boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(error, cause, enableSuppression, writableStackTrace);
    }

}
