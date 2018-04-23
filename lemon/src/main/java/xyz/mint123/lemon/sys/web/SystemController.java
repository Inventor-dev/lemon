package xyz.mint123.lemon.sys.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.omg.CORBA.ServerRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.mint123.lemon.core.base.BaseController;
import xyz.mint123.lemon.core.support.shiro.UsernamePasswordToken;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统控制器
 * @author lemon
 */
@Controller
public class SystemController extends BaseController {

    /**
     * 首页
     * @return
     */
    @GetMapping({"","/index.html"})
    public String index(ModelMap modelMap){
        return "sys/index";
    }

    /**
     * 登录页面
     * @param modelMap
     * @return
     */
    @GetMapping("/login.html")
    public String loginPage(ModelMap modelMap){
        return "sys/login";
    }

    /**
     * 登录 表单
     * @param modelMap
     * @return
     */
    @GetMapping("/loginForm.html")
    public String loginFrom(ModelMap modelMap){
        return "sys/loginForm";
    }

    /**
     * 登录
     * @return
     */
    @PostMapping("/login")
    public String doLogin(String loginName, String password, @RequestParam(defaultValue = "false") boolean rememberMe,
                          String captcha, ModelMap modelMap,HttpServletRequest request){
        try {
            String host = request.getRemoteHost();
            UsernamePasswordToken token = new UsernamePasswordToken(loginName,password,rememberMe,host,captcha);
            SecurityUtils.getSubject().login(token);
        } catch (Exception e) {
            modelMap.addAttribute("message",e.getMessage());
            return "redirect:/login.html";
        }
        return "redirect:/index.html";
    }

}
