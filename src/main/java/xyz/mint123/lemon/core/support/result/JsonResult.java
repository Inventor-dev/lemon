package xyz.mint123.lemon.core.support.result;

import java.io.Serializable;

/**
 * 返回数据 基类
 *
 * @author lemon
 * @version 2018/2/28
 */
public class JsonResult<E> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 是否执行成功
     */
    protected boolean success;

    /**
     * 返回 消息
     */
    protected String message;
    /**
     * 返回数据内容
     */
    protected E data;

    public JsonResult() {
        super();
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
