package xyz.lemone.lemon.system.authorization.boot;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManagerResolver;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.server.resource.authentication.OpaqueTokenReactiveAuthenticationManager;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.ServerWebExchangeDelegatingReactiveAuthenticationManagerResolver;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import org.springframework.web.server.ServerWebExchange;
import xyz.lemone.lemon.security.web.server.authentication.AccountLoginAuthenticationManager;
import xyz.lemone.lemon.security.web.server.authentication.ServerLoginAuthenticationConverter;
import xyz.lemone.lemon.security.web.server.authentication.ServerLoginSpec;
import xyz.lemone.lemon.security.web.server.authentication.ServerLoginSuccessHandler;
import xyz.lemone.lemon.support.Constants;
import xyz.lemone.lemon.system.authorization.OAuth2TokenType;
import xyz.lemone.lemon.system.authorization.service.InMemoryOAuth2AuthorizationService;
import xyz.lemone.lemon.system.authorization.service.OAuth2AuthorizationService;
import xyz.lemone.lemon.system.user.manager.SystemUserManager;

/**
 * WebSecurityConfig.
 *
 * @author lemon
 */
@Configuration
@EnableWebFluxSecurity
public class WebSecurityConfig {
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private SystemUserManager systemUserManager;
    
    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        //@formatter:off
        return ServerLoginSpec.serveLogin(http)
                .loginPath("/api/login/*")
                .authenticationConverter(new ServerLoginAuthenticationConverter(objectMapper))
                .account(accountSpec -> accountSpec.authenticationManager(accountLoginAuthenticationManager()))
                .authenticationSuccessHandler(new ServerLoginSuccessHandler(objectMapper,oAuth2AuthorizationService()))
                .config()
                .csrf(csrfSpec -> csrfSpec.disable())
                .authorizeExchange( authorize ->
                        authorize.pathMatchers(Constants.API_PREFIX + "/**").authenticated()
                                .anyExchange().permitAll())
                .oauth2ResourceServer(
                    resourceServerSpec -> resourceServerSpec.authenticationManagerResolver(oauth2AuthenticationManager()))
                .build();
        // @formatter:on
    }
    
    
    private AccountLoginAuthenticationManager accountLoginAuthenticationManager() {
        AccountLoginAuthenticationManager manager = new AccountLoginAuthenticationManager();
        manager.setSystemUserManager(systemUserManager);
        return manager;
    }
    
    
    ReactiveAuthenticationManagerResolver<ServerWebExchange> oauth2AuthenticationManager() {
        return ServerWebExchangeDelegatingReactiveAuthenticationManagerResolver.builder()
                .add(ServerWebExchangeMatchers.anyExchange(), new OpaqueTokenReactiveAuthenticationManager(
                        token -> oAuth2AuthorizationService().findByToken(token, OAuth2TokenType.ACCESS_TOKEN)
                                .map(oAuth2Authorization -> new DefaultOAuth2User(null,
                                        oAuth2Authorization.getAttributes(), "user")))).build();
    }
    
    @Bean
    OAuth2AuthorizationService oAuth2AuthorizationService() {
        return new InMemoryOAuth2AuthorizationService();
    }
    
}
