package xyz.lemone.lemon.system.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.relational.core.mapping.Table;
import xyz.lemone.lemon.support.entity.RecordDO;

/**
 * SYS 模块 用户实体.
 *
 * @author lemon
 * @version 2018/1/23
 */
@Entity
@Table(name = "system_user")
public class SystemUserDO extends RecordDO {
    
    private static final long serialVersionUID = -1;
    
    /**
     * 用户ID.
     */
    @Id
    @Column(name = "userid")
    private String userid;
    
    /**
     * 登陆名.
     */
    @Column(name = "username")
    private String username;
    
    /**
     * 密码.
     */
    @Column(name = "password")
    private String password;
    
    /**
     * 姓名.
     */
    @Column(name = "real_name")
    private String realName;
    
    /**
     * 头像.
     */
    @Column(name = "avatar")
    private String avatar;
    
    /**
     * 手机号.
     */
    @Column(name = "phone")
    private String phone;
    
    /**
     * 邮箱.
     */
    @Column(name = "email")
    private String email;
    
    public SystemUserDO() {
    }
    
    public String getUserid() {
        return userid;
    }
    
    public void setUserid(String userid) {
        this.userid = userid;
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
    
    public String getRealName() {
        return realName;
    }
    
    public void setRealName(String realName) {
        this.realName = realName;
    }
    
    public String getAvatar() {
        return avatar;
    }
    
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE).append("userid", userid).append("username", username)
                .append("password", password, false).append("realName", realName).append("avatar", avatar)
                .append("phone", phone).append("email", email).toString();
    }
}
