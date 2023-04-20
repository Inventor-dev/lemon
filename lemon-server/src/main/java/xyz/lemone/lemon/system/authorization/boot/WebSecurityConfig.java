package xyz.lemone.lemon.system.authorization.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManagerResolver;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.core.DefaultOAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.ServerWebExchangeDelegatingReactiveAuthenticationManagerResolver;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import xyz.lemone.lemon.support.Constants;
import xyz.lemone.lemon.system.authorization.OAuth2TokenType;
import xyz.lemone.lemon.system.authorization.service.InMemoryOAuth2AuthorizationService;
import xyz.lemone.lemon.system.authorization.service.OAuth2AuthorizationService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * WebSecurityConfig.
 *
 * @author lemon
 */
@Configuration
@EnableWebFluxSecurity
public class WebSecurityConfig {
    
    private static final Pattern APP_AUTHORIZATION_PATTERN = Pattern.compile(
            "^Bearer lemon.(?<token>[a-zA-Z0-9-._~+/]+=*)$", Pattern.CASE_INSENSITIVE);
    
    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        //@formatter:off
        return http.csrf().disable()
                .authorizeExchange((authorize) -> authorize
                        .pathMatchers(Constants.API_PREFIX + "/**").authenticated()
                        .anyExchange().permitAll())
                .oauth2ResourceServer(resourceServerSpec ->
                        resourceServerSpec.authenticationManagerResolver(multiAuthenticationManager())
                ).build();
        // @formatter:on
    }
    
    @Bean
    ReactiveAuthenticationManagerResolver<ServerWebExchange> multiAuthenticationManager() {
        return ServerWebExchangeDelegatingReactiveAuthenticationManagerResolver.builder().add(exchange -> {
            String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            Matcher matcher = APP_AUTHORIZATION_PATTERN.matcher(token);
            if (matcher.find()) {
                return ServerWebExchangeMatcher.MatchResult.match();
            }
            return ServerWebExchangeMatcher.MatchResult.notMatch();
        }, applicationAuthenticationManager()).build();
        
    }
    
    @Bean
    OAuth2AuthorizationService oAuth2AuthorizationService() {
        return new InMemoryOAuth2AuthorizationService();
    }
    
    ReactiveAuthenticationManager applicationAuthenticationManager() {
        //@formatter:on
        return authentication -> Mono.justOrEmpty(authentication)
                .filter(BearerTokenAuthenticationToken.class::isInstance).cast(BearerTokenAuthenticationToken.class)
                .map(BearerTokenAuthenticationToken::getToken)
                .flatMap(token -> oAuth2AuthorizationService().findByToken(token, OAuth2TokenType.ACCESS_TOKEN))
                .map(oAuth2Authorization -> {
                    OAuth2AccessToken accessToken = oAuth2Authorization.getAccessToken().getToken();
                    DefaultOAuth2AuthenticatedPrincipal principal = new DefaultOAuth2AuthenticatedPrincipal(
                            oAuth2Authorization.getAttributes(), null);
                    return new BearerTokenAuthentication(principal, accessToken, principal.getAuthorities());
                });
        // @formatter:on
    }
    
}
