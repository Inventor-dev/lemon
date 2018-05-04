package xyz.mint123.lemon.sys.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import xyz.mint123.lemon.core.base.BaseEntity;

/**
 * SYS 模块 用户实体
 * @author lemon
 * @version 2018/1/23
 */
@TableName("sys_user")
public class User extends BaseEntity<String> {

    private static final long serialVersionUID = -7717224603868173832L;

    /**
     * 用户姓名
     */
    private String name;
    /**
     * 人员 编码
     */
    private String code;
    /**
     * 登陆名
     */
    private String loginName;
    /**
     * 登陆名
     */
    private String password;


    public User() {
    }

    public User(String id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
