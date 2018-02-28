package xyz.mint123.lemon.core.result;

import java.io.Serializable;

/**
 * 返回数据 基类
 * @author lemon
 * @version 2018/2/28
 */
public class BaseResult<D> implements Serializable {

    /**
     * 返回状态码
     */
    protected String code;
    /**
     * 返回消息内容
     */
    protected String msg;
    /**
     * 返回数据
     */
    protected D data;

}
