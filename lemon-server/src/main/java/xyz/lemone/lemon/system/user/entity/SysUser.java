package xyz.lemone.lemon.system.user.entity;

import cn.org.atool.fluent.mybatis.annotation.FluentMybatis;
import cn.org.atool.fluent.mybatis.annotation.TableField;
import cn.org.atool.fluent.mybatis.annotation.TableId;
import cn.org.atool.fluent.mybatis.base.IEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import xyz.lemone.lemon.common.base.entity.RecordDO;

/**
 * SYS 模块 用户实体.
 *
 * @author lemon
 * @version 2018/1/23
 */
@FluentMybatis(table = "sys_user")
public class SysUser extends RecordDO implements IEntity {

    private static final long serialVersionUID = -7717224603868173832L;

    @TableId("user_id")
    private String userId;

    /**
     * 登陆名.
     */
    private String username;

    /**
     * 登陆名.
     */
    private String password;

    /**
     * 手机号.
     */
    private String phone;

    /**
     * 真实姓名.
     */
    @TableField("real_name")
    private String realName;

    /**
     * 人员 编码.
     */
    @TableField("user_code")
    private String userCode;

    public SysUser() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("createTime", createTime)
                .append("updateTime", updateTime)
                .append("userId", userId)
                .append("username", username)
                .append("password", password)
                .append("phone", phone)
                .append("realName", realName)
                .append("userCode", userCode)
                .toString();
    }
}
