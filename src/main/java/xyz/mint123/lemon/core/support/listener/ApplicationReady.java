package xyz.mint123.lemon.core.support.listener;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import xyz.mint123.lemon.core.util.SpringContextHolder;
import xyz.mint123.lemon.sys.service.UserService;
import xyz.mint123.lemon.sys.util.UserUtils;

/**
 * 系统 启动监听
 * @author lemon
 * @version 2018/2/9
 */
@Component
public class ApplicationReady implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        System.out.println("");
        System.out.println("");
        System.out.println("     ╭────╮         ╭───╮   ╭──── ────╮      ╭───╮       ╭──────╮               ");
        System.out.println("     ╰╮  ╭╯        /  ╭─╯   │    │    │     ╱     ╲      │       ╲              ");
        System.out.println("      │  │        /   ╰─╮   │ ─     ─ │    │       │     │   _   │              ");
        System.out.println("      │  │       │    ╭─╯   │   ╲_╱   │    │   O   │     │   │   │              ");
        System.out.println("      │  │       │    ╰─╮   │         │    │   │   │     │   │   │              ");
        System.out.println("     ╭╯  ╰────╮  │      │   │    │    │     ╲     ╱      │   │   │              ");
        System.out.println("     ╰────────╯  ╰──────╯   ╰────╯────╯      ╰───╯       ╰───┴───╯      v 1.0   ");
        System.out.println("");
        System.out.println("Welcome to use Lemon ");
//        初始化用户工具类
        UserUtils.setUserService(SpringContextHolder.getBean(UserService.class));
    }
}
