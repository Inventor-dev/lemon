package xyz.mint123.lemon.sys.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import xyz.mint123.lemon.core.base.BaseController;
import xyz.mint123.lemon.sys.web.vo.LoginResult;
import xyz.mint123.lemon.sys.web.vo.UserVo;

@RestController
public class LoginUserController extends BaseController {

    /**
     * 当前登录用户信息
     */
    @GetMapping("${project.apiPath}/currentUser")
    public Mono<UserVo> currentuser() {
        UserVo userVo = new UserVo();
        userVo.setId("0");
        userVo.setName("SYSTEM");
        userVo.setAvatar("https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png");
        userVo.setNotifyCount(12L);
        return Mono.just(userVo);
    }


    /**
     * 登录 情况返回
     * 真正的登陆 由shiro 处理
     */
    @ResponseBody
    @PostMapping("${project.apiPath}/login/{type}")
    public Mono<LoginResult> doLogin(@PathVariable String type) {
        LoginResult result = new LoginResult();
        result.setStatus(LoginResult.LOGIN_SUCCESS);
        result.setMessage("Login Success");
        result.setType(type);
        result.setAuthority("admin");
        return Mono.just(result);
    }


    /**
     * 登出
     */
    @ResponseBody
    @GetMapping("${project.apiPath}/logout")
    public void logout() {
        SecurityUtils.getSubject().logout();
    }


}
