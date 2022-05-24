package xyz.lemone.lemon.common.support.exception.impl;

import xyz.lemone.lemon.common.support.error.IError;

/**
 * 业务处理失败异常.
 *
 * @author lemon
 */
public abstract class ProcessFailedException extends AbstractRuntimeException {

    private static final long serialVersionUID = -1291449181632984701L;

    public ProcessFailedException() {
    }

    public ProcessFailedException(IError error) {
        super(error);
    }

    public ProcessFailedException(IError error, Throwable cause) {
        super(error, cause);
    }

    public ProcessFailedException(IError error, Throwable cause, boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(error, cause, enableSuppression, writableStackTrace);
    }

}
