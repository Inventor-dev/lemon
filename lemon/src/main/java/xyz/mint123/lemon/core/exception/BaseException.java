package xyz.mint123.lemon.core.exception;

/**
 * 异常基类
 * @author lemon
 * @version 2018/2/28
 */
public class BaseException extends RuntimeException {

    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

}
