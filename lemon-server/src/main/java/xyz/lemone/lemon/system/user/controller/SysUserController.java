package xyz.lemone.lemon.system.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import xyz.lemone.lemon.support.Constants;
import xyz.lemone.lemon.support.response.SingleResponse;
import xyz.lemone.lemon.system.user.entity.SystemUserDO;
import xyz.lemone.lemon.system.user.service.SystemUserService;

/**
 * SysUserController.
 *
 * @author lime
 */
@RestController
@RequestMapping(Constants.API_PREFIX + "/sys/user")
public class SysUserController {
    
    @Autowired
    private SystemUserService systemUserService;
    
    @GetMapping()
    public Mono<SingleResponse<SystemUserDO>> loadByPhone(SystemUserDO param) {
        return Mono.just(SingleResponse.SUCCESS);
    }
    
}
