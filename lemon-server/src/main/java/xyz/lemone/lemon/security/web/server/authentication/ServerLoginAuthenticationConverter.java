package xyz.lemone.lemon.security.web.server.authentication;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.IOException;

/**
 * @author lemon
 */
public class ServerLoginAuthenticationConverter implements ServerAuthenticationConverter {
    
    public static final String AUTH_TYPE_ACCOUNT = "account";
    
    private String typeParameter = "type";
    
    private String usernameParameter = "username";
    
    private String passwordParameter = "password";
    
    private String autoLoginParameter = "autoLogin";
    
    
    private ObjectMapper objectMapper;
    
    public ServerLoginAuthenticationConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    
    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        return DataBufferUtils.join(exchange.getRequest().getBody())
                .handle((dataBuffer, synchronousSink) -> synchronousSink.next(doConvert(exchange, dataBuffer)));
    }
    
    
    private Authentication doConvert(ServerWebExchange exchange, DataBuffer dataBuffer) {
        try {
            
            JsonNode jsonNode = objectMapper.readTree(dataBuffer.asInputStream());
            String type = jsonNode.get(typeParameter).asText();
            exchange.getResponse().getHeaders().set("loginType", type);
            switch (type) {
                case AUTH_TYPE_ACCOUNT: {
                    String username = jsonNode.get(usernameParameter).asText();
                    String password = jsonNode.get(passwordParameter).asText();
                    Boolean autoLogin = jsonNode.get(autoLoginParameter).asBoolean();
                    return new AccountAuthenticationToken(username, password, autoLogin);
                }
                default:
                    return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    
}
