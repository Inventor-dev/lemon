package xyz.lemone.lemon.common.support.error;

/**
 * 通用错误信息.
 *
 * @author lime
 */
public interface IError {

    /**
     * 获取错误码.
     *
     * @return
     */
    String getErrorCode();

    /**
     * 获取错误描述.
     *
     * @return
     */
    String getErrorMessage();

}
