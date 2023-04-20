package xyz.lemone.lemon.system.authorization;

import org.springframework.security.oauth2.core.AbstractOAuth2Token;

import java.time.Instant;

/**
 * An implementation of an {@link AbstractOAuth2Token} representing an OAuth 2.0 Authorization Code Grant.
 *
 * @author Joe Grandja
 * @see AbstractOAuth2Token
 * @see <a target="_blank" href="https://tools.ietf.org/html/rfc6749#section-4.1">Section 4.1 Authorization Code
 * Grant</a>
 * @since 0.0.3
 */
public class OAuth2AuthorizationCode extends AbstractOAuth2Token {
    
    /**
     * Constructs an {@code OAuth2AuthorizationCode} using the provided parameters.
     *
     * @param tokenValue the token value
     * @param issuedAt   the time at which the token was issued
     * @param expiresAt  the time at which the token expires
     */
    public OAuth2AuthorizationCode(String tokenValue, Instant issuedAt, Instant expiresAt) {
        super(tokenValue, issuedAt, expiresAt);
    }
    
}
