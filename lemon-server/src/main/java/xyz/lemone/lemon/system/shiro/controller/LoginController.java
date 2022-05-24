package xyz.lemone.lemon.system.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.lemone.lemon.common.Constants;
import xyz.lemone.lemon.common.response.SingleResponse;

/**
 * LoginController.
 *
 * @author lime
 */
@RestController
public class LoginController {

    /**
     * 登录后操作.
     * 真正的登录由filter完成
     *
     * @return
     */
    @RequestMapping(Constants.API_PREFIX + "/login/**")
    public SingleResponse login() {
        Object principal = SecurityUtils.getSubject().getPrincipal();
        //  token 操作
        return SingleResponse.SUCCESS;
    }

}
