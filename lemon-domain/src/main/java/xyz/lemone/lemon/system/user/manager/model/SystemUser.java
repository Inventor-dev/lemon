package xyz.lemone.lemon.system.user.manager.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 系统用户.
 */
public class SystemUser implements Serializable {
    
    /**
     * 用户ID.
     */
    private String userid;
    
    /**
     * 登陆名.
     */
    private String username;
    
    /**
     * 密码.
     */
    private String password;
    
    /**
     * 姓名.
     */
    private String realName;
    
    /**
     * 头像.
     */
    private String avatar;
    
    /**
     * 手机号.
     */
    private String phone;
    
    /**
     * 邮箱.
     */
    private String email;
    
    public SystemUser() {
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
        return new ToStringBuilder(this).append("userid", userid).append("username", username)
                .append("password", password, false).append("realName", realName).append("avatar", avatar)
                .append("phone", phone).append("email", email).toString();
    }
}
