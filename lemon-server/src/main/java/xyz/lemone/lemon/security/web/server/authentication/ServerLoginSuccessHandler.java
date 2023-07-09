package xyz.lemone.lemon.security.web.server.authentication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.endpoint.DefaultOAuth2AccessTokenResponseMapConverter;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import reactor.core.publisher.Mono;
import xyz.lemone.lemon.support.response.SingleResponse;
import xyz.lemone.lemon.system.authorization.OAuth2Authorization;
import xyz.lemone.lemon.system.authorization.client.RegisteredClient;
import xyz.lemone.lemon.system.authorization.service.OAuth2AuthorizationService;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class ServerLoginSuccessHandler implements ServerAuthenticationSuccessHandler {
    
    public static final RegisteredClient APP_CLIENT = RegisteredClient.withId("app").clientId("app")
            .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS).build();
    
    
    private ObjectMapper objectMapper;
    
    private OAuth2AuthorizationService oAuth2AuthorizationService;
    
    private DefaultOAuth2AccessTokenResponseMapConverter converter = new DefaultOAuth2AccessTokenResponseMapConverter();
    
    public ServerLoginSuccessHandler(ObjectMapper objectMapper, OAuth2AuthorizationService oAuth2AuthorizationService) {
        this.objectMapper = objectMapper;
        this.oAuth2AuthorizationService = oAuth2AuthorizationService;
    }
    
    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange exchange, Authentication authentication) {
        OAuth2Authorization oAuth2Authorization = OAuth2Authorization.withRegisteredClient(APP_CLIENT).accessToken(
                        new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER,
                                "lemon." + RandomStringUtils.randomAlphabetic(12), Instant.now(),
                                Instant.now().plusSeconds(2000L))).refreshToken(
                        new OAuth2RefreshToken(RandomStringUtils.randomAlphabetic(12), Instant.now().plusSeconds(2000L)))
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN).principalName("lemon")
                .attribute("user", authentication.getPrincipal()).build();
        oAuth2AuthorizationService.save(oAuth2Authorization);
        OAuth2AccessToken accessToken = oAuth2Authorization.getAccessToken().getToken();
        OAuth2RefreshToken refreshToken = oAuth2Authorization.getRefreshToken().getToken();
        OAuth2AccessTokenResponse.Builder builder = OAuth2AccessTokenResponse.withToken(accessToken.getTokenValue())
                .tokenType(accessToken.getTokenType()).scopes(accessToken.getScopes());
        if (accessToken.getIssuedAt() != null && accessToken.getExpiresAt() != null) {
            builder.expiresIn(ChronoUnit.SECONDS.between(accessToken.getIssuedAt(), accessToken.getExpiresAt()));
        }
        if (refreshToken != null) {
            builder.refreshToken(refreshToken.getTokenValue());
        }
        ServerHttpResponse response = exchange.getExchange().getResponse();
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        try {
            return response.writeWith(Mono.just(response.bufferFactory()
                    .wrap(objectMapper.writeValueAsBytes(SingleResponse.of(converter.convert(builder.build()))))));
        } catch (JsonProcessingException e) {
            return Mono.error(e);
        }
    }
}
