package xyz.lemone.lemon.common.response;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 统一前端返回数据结构
 */
public class Response<D> implements Serializable {

    /**
     * 成功
     */
    public static final Response SUCCESS = new Response(null);

    /**
     * 执行成功与否
     */
    private Boolean success;

    /**
     * 错误码/错误消息KEY
     */
    private String errorCode;

    /**
     * 默认错误描述
     */
    private String errorMessage;

    /**
     * 返回数据
     */
    private D data;

    public Response(D data) {
        this.success = Boolean.TRUE;
        this.data = data;
    }

    public Response(Boolean success, String errorCode,String errorMessage ) {
        this.success = success;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * 成功数据返回
     * @param data
     * @param <D>
     * @return
     */
    public static <D> Response<D>  success(D data){
        return new Response<>(data);
    }

    /**
     * 成功数据返回
     * @param <D>
     * @return
     */
    public static <D> Response<D>  error(String errorCode,String errorMessage){
        return new Response<>(Boolean.FALSE,errorCode,errorMessage);
    }

    public Boolean getSuccess() {
        return success;
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

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("success", success)
                .append("errorCode", errorCode)
                .append("errorMessage", errorMessage)
                .append("data", data)
                .toString();
    }
}
