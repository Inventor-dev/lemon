package xyz.mint123.lemon.sys.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.mint123.lemon.core.base.BaseController;

/**
 * 系统控制器
 * @author lemon
 */
@Controller
@RequestMapping("/")
public class SystemController extends BaseController {

    /**
     * 首页
     * @return
     */
    @GetMapping("/index.html")
    public String index(ModelMap modelMap){
        return "sys/index";
    }

    /**
     * 登录页面
     * @param modelMap
     * @return
     */
    @GetMapping("/login.html")
    public String login(ModelMap modelMap){
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



}
