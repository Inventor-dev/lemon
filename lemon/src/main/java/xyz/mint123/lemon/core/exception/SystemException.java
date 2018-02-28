package xyz.mint123.lemon.core.exception;

/**
 * 系统异常
 * @author lemon
 * @version 2018/2/28
 */
public class SystemException extends BaseException {

    public SystemException() {
        super();
    }

    public SystemException(String message) {
        super(message);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }


}
