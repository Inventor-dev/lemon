package xyz.lemone.lemon.system.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import xyz.lemone.lemon.support.Constants;
import xyz.lemone.lemon.support.response.SingleResponse;
import xyz.lemone.lemon.system.user.entity.SysUser;
import xyz.lemone.lemon.system.user.service.SysUserService;

/**
 * SysUserController.
 *
 * @author lime
 */
@RestController
@RequestMapping(Constants.API_PREFIX + "/sys/user")
public class SysUserController {
    
    @Autowired
    private SysUserService sysUserService;
    
    @GetMapping("phone/{phone}")
    public Mono<SingleResponse<SysUser>> loadByPhone(@PathVariable("phone") String phone) {
        return sysUserService.loadByPhone(phone).map(sysUser -> SingleResponse.of(sysUser))
                .defaultIfEmpty(SingleResponse.SUCCESS);
    }
    
}
