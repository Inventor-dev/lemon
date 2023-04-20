package xyz.lemone.lemon.support.error.impl;

import org.apache.commons.lang3.builder.ToStringBuilder;
import xyz.lemone.lemon.support.error.IError;

/**
 * 通用异常信息.
 *
 * @author lemon
 */
public class ProcessError implements IError {

    /**
     * 错误码/错误消息KEY.
     */
    public final String errorCode;

    /**
     * 默认错误描述.
     */
    public final String errorMessage;

    protected ProcessError(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public static ProcessError of(String errorCode, String errorMessage) {
        return new ProcessError(errorCode, errorMessage);
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("errorCode", errorCode)
                .append("errorMessage", errorMessage)
                .toString();
    }
}
