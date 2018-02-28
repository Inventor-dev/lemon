package xyz.mint123.lemon.core.exception;

/**
 * 业务异常
 * @author lemon
 * @version 2018/2/28
 */
public class BusinessException extends BaseException {


    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }


}
