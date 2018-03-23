package xyz.mint123.lemon.sys.web;

import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.mint123.lemon.core.base.BaseController;
import xyz.mint123.lemon.sys.entity.User;
import xyz.mint123.lemon.sys.service.UserService;

import java.util.List;

/**
 * @author lemon
 * @version 2018/2/26
 */
@RestController
@RequestMapping("/sys/user")
public class UserController extends BaseController{

	private UserService userService;
	
    public UserController(UserService userService) {
        this.userService = userService;
    }

	@GetMapping("/{id}")
    public Mono<User> getUser(@PathVariable String id) {
        return Mono.just(userService.selectOne(id));
    }

    @GetMapping("/list")
    public Flux<User> selectList(User entity) {
        return Flux.fromIterable(userService.selectList(entity));
    }




}
