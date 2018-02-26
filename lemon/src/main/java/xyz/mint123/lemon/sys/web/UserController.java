package xyz.mint123.lemon.sys.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.mint123.lemon.sys.entity.User;
import xyz.mint123.lemon.sys.service.UserService;

import java.util.List;

/**
 * @author lemon
 * @version 2018/2/26
 */
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/sys/user/list")
    public List<User> selectList(){
        User entity = new User();
        return userService.selectList(entity);
    };

}
