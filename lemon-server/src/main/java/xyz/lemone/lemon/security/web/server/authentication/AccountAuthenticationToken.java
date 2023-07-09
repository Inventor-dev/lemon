package xyz.lemone.lemon.security.web.server.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * 账户登录Token.
 *
 * @author lemon
 */
public class AccountAuthenticationToken extends AbstractAuthenticationToken {
    
    /**
     * 用户名.
     */
    private String username;
    
    /**
     * 密码.
     */
    private String password;
    
    /**
     * 自动登录.
     */
    private Boolean autoLogin;
    
    public AccountAuthenticationToken(String username, String password, Boolean autoLogin) {
        super(null);
        this.username = username;
        this.password = password;
        this.autoLogin = autoLogin;
    }
    
    @Override
    public String getCredentials() {
        return password;
    }
    
    @Override
    public String getPrincipal() {
        return username;
    }
    
    public Boolean getAutoLogin() {
        return autoLogin;
    }
}
