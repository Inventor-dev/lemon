package xyz.lemone.lemon.security.web.server.authentication;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.ReactiveUserDetailsPasswordService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import xyz.lemone.lemon.security.authentication.UserInfo;

/**
 * @author lemon
 */
public class AccountLoginAuthenticationManager implements ReactiveAuthenticationManager {
    
    protected final Log logger = LogFactory.getLog(getClass());
    
    private ReactiveUserDetailsService userDetailsService;
    
    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
    
    private PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    
    private ReactiveUserDetailsPasswordService userDetailsPasswordService;
    
    private Scheduler scheduler = Schedulers.boundedElastic();
    
    private UserDetailsChecker preAuthenticationChecks = this::defaultPreAuthenticationChecks;
    
    private void defaultPreAuthenticationChecks(UserDetails user) {
        if (!user.isAccountNonLocked()) {
            this.logger.debug("User account is locked");
            throw new LockedException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.locked",
                    "User account is locked"));
        }
        if (!user.isEnabled()) {
            this.logger.debug("User account is disabled");
            throw new DisabledException(
                    this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.disabled", "User is disabled"));
        }
        if (!user.isAccountNonExpired()) {
            this.logger.debug("User account is expired");
            throw new AccountExpiredException(
                    this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.expired",
                            "User account has expired"));
        }
    }
    
    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return doAccountAuthenticate((AccountAuthenticationToken) authentication).map(this::createAuthenticationToken);
    }
    
    private Mono<UserDetails> doAccountAuthenticate(AccountAuthenticationToken accountToken) {
        String username = accountToken.getPrincipal();
        String presentedPassword = accountToken.getCredentials();
        // @formatter:off
        return userDetailsService.findByUsername(username)
                .doOnNext(preAuthenticationChecks::check)
                .publishOn(scheduler)
                .filter((userDetails) -> this.passwordEncoder.matches(presentedPassword, userDetails.getPassword()))
                .switchIfEmpty(Mono.defer(() -> Mono.error(new BadCredentialsException("Invalid Credentials"))))
                .flatMap((userDetails) -> upgradeEncodingIfNecessary(userDetails, presentedPassword));
        // @formatter:on
    }
    
    private Mono<UserDetails> upgradeEncodingIfNecessary(UserDetails userDetails, String presentedPassword) {
        boolean upgradeEncoding =
                userDetailsPasswordService != null && passwordEncoder.upgradeEncoding(userDetails.getPassword());
        if (upgradeEncoding) {
            String newPassword = passwordEncoder.encode(presentedPassword);
            return userDetailsPasswordService.updatePassword(userDetails, newPassword);
        }
        return Mono.just(userDetails);
    }
    
    private LoginAuthentication createAuthenticationToken(UserDetails userDetails) {
        return LoginAuthentication.authenticated(UserInfo.UserInfoBuilder.anUserInfo().build(), null, null);
    }
    
    
    public ReactiveUserDetailsService getUserDetailsService() {
        return userDetailsService;
    }
    
    public void setUserDetailsService(ReactiveUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
