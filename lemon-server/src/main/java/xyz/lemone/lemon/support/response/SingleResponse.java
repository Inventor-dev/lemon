package xyz.lemone.lemon.support.response;

import java.util.StringJoiner;

/**
 * Response with single record to return .
 *
 * @param <T> record type
 * @author lemon
 */
public class SingleResponse<T> extends Response {
    
    /**
     * 成功.
     */
    public static final SingleResponse SUCCESS = SingleResponse.of(null);
    
    /**
     * data.
     */
    private T data;
    
    public SingleResponse() {
    }
    
    /**
     * of.
     *
     * @param data record
     * @param <T>  record type
     * @return
     */
    public static <T> SingleResponse<T> of(T data) {
        SingleResponse<T> singleResponse = new SingleResponse<>();
        singleResponse.setSuccess(Boolean.TRUE);
        singleResponse.setData(data);
        return singleResponse;
    }
    
    /**
     * buildFailure.
     *
     * @param errCode    错误码
     * @param errMessage 默认错误描述
     * @return
     */
    public static SingleResponse buildFailure(String errCode, String errMessage) {
        SingleResponse response = new SingleResponse();
        response.setSuccess(Boolean.FALSE);
        response.setErrorCode(errCode);
        response.setErrorMessage(errMessage);
        return response;
    }
    
    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
    
    @Override
    public String toString() {
        return new StringJoiner(", ", SingleResponse.class.getSimpleName() + "[", "]").add("data=" + data)
                .add("success=" + success).add("errorCode='" + errorCode + "'")
                .add("errorMessage='" + errorMessage + "'").toString();
    }
}
