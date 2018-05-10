package xyz.mint123.lemon.core.support.result;

import java.io.Serializable;

/**
 * 返回数据 基类
 * @author lemon
 * @version 2018/2/28
 */
public class JsonResult<D> implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 返回状态码
     */
    protected String code;
    /**
     * 返回消息内容
     */
    protected String msg;
    /**
     *  请求uri
     */
    protected String uri;
    /**
     * 返回数据
     */
    protected D data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
