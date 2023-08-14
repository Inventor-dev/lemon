package xyz.lemone.lemon.system.authorization.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import xyz.lemone.lemon.security.authentication.UserInfo;
import xyz.lemone.lemon.support.Constants;
import xyz.lemone.lemon.support.response.SingleResponse;
import xyz.lemone.lemon.system.user.manager.SystemUserManager;
import xyz.lemone.lemon.system.user.manager.model.SystemUser;

import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;

/**
 * 等录控制器.
 *
 * @author lemon
 */
@RestController
@RequestMapping(Constants.API_PREFIX)
public class LoginController {
    
    @Autowired
    private SystemUserManager systemUserManager;
    
    @GetMapping("currentUser")
    public Mono<SingleResponse> currentUser(@AuthenticationPrincipal DefaultOAuth2User obj) {
        UserInfo user = obj.getAttribute("user");
        HashMap<Object, Object> data = new HashMap<>();
        data.put("access","admin");
        return systemUserManager.findByUserid(user.getUserid())
                .singleOptional()
                .map(systemUser -> SingleResponse.of(systemUser.orElse(null)));
    }
    
}
