package xyz.lemone.lemon.system.user.entity;

import cn.org.atool.fluent.mybatis.annotation.FluentMybatis;
import cn.org.atool.fluent.mybatis.annotation.TableId;
import cn.org.atool.fluent.mybatis.base.IEntity;

/**
 * SYS 模块 用户实体
 * @author lemon
 * @version 2018/1/23
 */
@FluentMybatis(table = "sys_user")
public class User implements IEntity {

    private static final long serialVersionUID = -7717224603868173832L;

    @TableId("user_id")
    private String userId;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
