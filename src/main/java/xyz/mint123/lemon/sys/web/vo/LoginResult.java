package xyz.mint123.lemon.sys.web.vo;

import xyz.mint123.lemon.core.support.result.JsonResult;

/**
 * 登录信息
 */
public class LoginResult extends JsonResult<String> {

    /**
     * 登录成功
     */
    public static  final String LOGIN_SUCCESS="ok";

    /**
     * 登录状态
     */
    private String status;
    /**
     * 登陆类型
     */
    private String type;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthority() {
        return data;
    }

    public void setAuthority(String authority) {
        this.data = authority;
    }
}
