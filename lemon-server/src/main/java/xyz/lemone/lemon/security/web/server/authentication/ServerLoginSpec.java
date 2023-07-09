package xyz.lemone.lemon.security.web.server.authentication;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.authentication.ServerWebExchangeDelegatingReactiveAuthenticationManagerResolver;
import org.springframework.security.web.server.authentication.WebFilterChainServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;
import xyz.lemone.lemon.support.Constants;

import java.util.Objects;

public class ServerLoginSpec {
    
    public static final String DEFAULT_LOGIN_PATH = Constants.API_PREFIX + "/login/*";
    
    protected ServerHttpSecurity serverHttpSecurity;
    
    private String loginPath = DEFAULT_LOGIN_PATH;
    
    private ServerAuthenticationConverter authenticationConverter;
    
    private ServerAuthenticationSuccessHandler authenticationSuccessHandler;
    
    private AccountSpec accountSpec;
    
    
    protected ServerLoginSpec(ServerHttpSecurity serverHttpSecurity) {
        this.serverHttpSecurity = serverHttpSecurity;
    }
    
    public static ServerLoginSpec serveLogin(ServerHttpSecurity serverHttpSecurity) {
        return new ServerLoginSpec(serverHttpSecurity);
    }
    
    public ServerLoginSpec loginPath(String loginPath) {
        if (StringUtils.hasText(loginPath)) {
            this.loginPath = loginPath;
        } else {
            this.loginPath = DEFAULT_LOGIN_PATH;
        }
        return this;
    }
    
    public ServerLoginSpec authenticationConverter(ServerAuthenticationConverter authenticationConverter) {
        Assert.notNull(authenticationConverter, "authenticationConverter cannot be null");
        this.authenticationConverter = authenticationConverter;
        return this;
    }
    
    public ServerLoginSpec authenticationSuccessHandler(
            ServerAuthenticationSuccessHandler authenticationSuccessHandler) {
        Assert.notNull(authenticationSuccessHandler, "authenticationSuccessHandler cannot be null");
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        return this;
    }
    
    
    public ServerLoginSpec account(Customizer<AccountSpec> accountCustomizer) {
        if (Objects.isNull(accountSpec)) {
            this.accountSpec = new AccountSpec();
        }
        accountCustomizer.customize(accountSpec);
        return this;
    }
    
    public ServerHttpSecurity config() {
        ServerWebExchangeDelegatingReactiveAuthenticationManagerResolver.Builder builder = ServerWebExchangeDelegatingReactiveAuthenticationManagerResolver.builder();
        if (Objects.nonNull(accountSpec)) {
            accountSpec.configure(builder);
        }
        AuthenticationWebFilter authenticationFilter = new AuthenticationWebFilter(builder.build());
        authenticationFilter.setRequiresAuthenticationMatcher(
                new PathPatternParserServerWebExchangeMatcher(loginPath, HttpMethod.POST));
        authenticationFilter.setServerAuthenticationConverter(authenticationConverter);
        authenticationFilter.setAuthenticationFailureHandler((webFilterExchange, exception) -> {
            ServerHttpResponse response = webFilterExchange.getExchange().getResponse();
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            return Mono.error(exception);
        });
        authenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        return serverHttpSecurity.addFilterAt(authenticationFilter, SecurityWebFiltersOrder.FORM_LOGIN);
    }
    
    public class AccountSpec {
        
        private ReactiveAuthenticationManager authenticationManager;
        
        public AccountSpec authenticationManager(ReactiveAuthenticationManager authenticationManager) {
            Assert.notNull(authenticationManager, "authenticationConverter cannot be null");
            this.authenticationManager = authenticationManager;
            return this;
        }
        
        public ServerLoginSpec disable() {
            ServerLoginSpec.this.accountSpec = null;
            return ServerLoginSpec.this;
        }
        
        
        public void configure(ServerWebExchangeDelegatingReactiveAuthenticationManagerResolver.Builder builder) {
            builder.add(exchange -> {
                String loginType = exchange.getResponse().getHeaders().getFirst("loginType");
                return "account".equals(loginType) ? ServerWebExchangeMatcher.MatchResult.match()
                        : ServerWebExchangeMatcher.MatchResult.notMatch();
            }, authenticationManager);
        }
    }
    
    
}
