package xyz.lemone.lemon.common.response;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 统一前端返回数据结构.
 *
 * @author lemon
 */
public class Response implements Serializable {

    private static final long serialVersionUID = 1;

    /**
     * 成功.
     */
    public static final Response SUCCESS = new Response();

    /**
     * 执行成功与否.
     */
    protected Boolean success;

    /**
     * 错误码/错误消息KEY.
     */
    protected String errorCode;

    /**
     * 默认错误描述.
     */
    protected String errorMessage;

    public Response() {
        this.success = Boolean.TRUE;
    }

    /**
     * buildFailure.
     * @param errCode error code
     * @param errMessage default error message
     * @return
     */
    public static Response buildFailure(String errCode, String errMessage) {
        Response response = new Response();
        response.setSuccess(Boolean.FALSE);
        response.setErrorCode(errCode);
        response.setErrorMessage(errMessage);
        return response;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("success", success)
                .append("errorCode", errorCode)
                .append("errorMessage", errorMessage)
                .toString();
    }
}
