package xyz.lemone.lemon.system.authorization.web.controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import xyz.lemone.lemon.support.Constants;
import xyz.lemone.lemon.support.response.SingleResponse;
import xyz.lemone.lemon.system.authorization.OAuth2Authorization;
import xyz.lemone.lemon.system.authorization.client.RegisteredClient;
import xyz.lemone.lemon.system.authorization.service.OAuth2AuthorizationService;
import xyz.lemone.lemon.system.authorization.web.cmd.AccountLoginParams;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * 等录控制器.
 *
 * @author lemon
 */
@RestController
@RequestMapping(Constants.API_PREFIX)
public class LoginController {
    
    public static final RegisteredClient APP_CLIENT = RegisteredClient.withId("app").clientId("app")
            .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS).build();
    
    @Autowired
    private OAuth2AuthorizationService oAuth2AuthorizationService;
    
    /**
     * 账户登录.
     */
    @PostMapping("login/account")
    public Mono<SingleResponse<OAuth2AccessTokenResponse>> accountLogin(AccountLoginParams loginParams) {
        OAuth2Authorization oAuth2Authorization = OAuth2Authorization.withRegisteredClient(APP_CLIENT).accessToken(
                        new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER,
                                "lemon." + RandomStringUtils.randomAlphabetic(12), Instant.now(),
                                Instant.now().plusSeconds(2000L))).refreshToken(
                        new OAuth2RefreshToken(RandomStringUtils.randomAlphabetic(12), Instant.now().plusSeconds(2000L)))
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN).principalName("lemon")
                .attribute("user", "123").build();
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
        return Mono.just(SingleResponse.of(builder.build()));
    }
    
}
