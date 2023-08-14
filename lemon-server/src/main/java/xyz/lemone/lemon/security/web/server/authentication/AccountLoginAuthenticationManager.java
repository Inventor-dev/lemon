package xyz.lemone.lemon.security.web.server.authentication;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import xyz.lemone.lemon.security.authentication.UserInfo;
import xyz.lemone.lemon.system.user.manager.SystemUserManager;
import xyz.lemone.lemon.system.user.manager.model.SystemUser;

/**
 * @author lemon
 */
public class AccountLoginAuthenticationManager implements ReactiveAuthenticationManager {
    
    protected final Log logger = LogFactory.getLog(getClass());
    
    private SystemUserManager systemUserManager;
    
    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
    
    private PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    
    private Scheduler scheduler = Schedulers.boundedElastic();
    
    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return doAccountAuthenticate((AccountAuthenticationToken) authentication).map(this::createAuthenticationToken);
    }
    
    private Mono<SystemUser> doAccountAuthenticate(AccountAuthenticationToken accountToken) {
        String username = accountToken.getPrincipal();
        String presentedPassword = accountToken.getCredentials();
        // @formatter:off
        return systemUserManager.findByUsername(username)
                .publishOn(scheduler)
                .filter((userDetails) -> this.passwordEncoder.matches(presentedPassword, userDetails.getPassword()))
                .switchIfEmpty(Mono.defer(() -> Mono.error(new BadCredentialsException("Invalid Credentials"))));
        // @formatter:on
    }
    
    private LoginAuthentication createAuthenticationToken(SystemUser systemUser) {
        return LoginAuthentication.authenticated(
                UserInfo.UserInfoBuilder.anUserInfo().withUserId(systemUser.getUserid())
                        .withUsername(systemUser.getUsername()).withRealName(systemUser.getRealName())
                        .withPhone(systemUser.getPhone()).build(), null, null);
    }
    
    
    public SystemUserManager getSystemUserManager() {
        return systemUserManager;
    }
    
    public void setSystemUserManager(SystemUserManager systemUserManager) {
        this.systemUserManager = systemUserManager;
    }
}
