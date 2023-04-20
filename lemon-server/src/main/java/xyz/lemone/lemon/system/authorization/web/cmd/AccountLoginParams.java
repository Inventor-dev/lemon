package xyz.lemone.lemon.system.authorization.web.cmd;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 账户登录参数.
 *
 * @author lemon
 */
public class AccountLoginParams implements Serializable {
    
    /**
     * 用户名.
     */
    private String username;
    
    /**
     * 密码.
     */
    private String password;
    
    public AccountLoginParams() {
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
    
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("username", username).append("password", password).toString();
    }
}
