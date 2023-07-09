package xyz.lemone.lemon.system.authorization.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import xyz.lemone.lemon.support.Constants;
import xyz.lemone.lemon.support.response.SingleResponse;

import java.util.HashMap;

/**
 * 等录控制器.
 *
 * @author lemon
 */
@RestController
@RequestMapping(Constants.API_PREFIX)
public class LoginController {
    
    @GetMapping("currentUser")
    public SingleResponse currentUser() {
        HashMap<Object, Object> data = new HashMap<>();
        data.put("access","admin");
        return SingleResponse.of(data);
    }
    
}
