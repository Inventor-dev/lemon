package xyz.lemone.lemon.security.web.server.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import xyz.lemone.lemon.security.authentication.UserInfo;

import java.util.Collection;

/**
 * @author lemon
 */
public class LoginAuthentication extends AbstractAuthenticationToken {
    
    private UserInfo principal;
    
    private String deviceId;
    
    public LoginAuthentication(UserInfo principal, String deviceId,
            Collection<? extends GrantedAuthority> authorities, Boolean authenticated) {
        super(authorities);
        this.principal = principal;
        this.deviceId = deviceId;
        super.setAuthenticated(authenticated);
    }
    
    public static LoginAuthentication authenticated(UserInfo principal, String deviceId,
            Collection<? extends GrantedAuthority> authorities) {
        return new LoginAuthentication(principal, deviceId, authorities,true);
    }
    
    
    @Override
    public String getCredentials() {
        return deviceId;
    }
    
    @Override
    public UserInfo getPrincipal() {
        return principal;
    }
    
}
