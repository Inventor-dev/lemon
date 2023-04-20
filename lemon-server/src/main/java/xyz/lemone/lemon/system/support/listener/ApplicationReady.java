package xyz.lemone.lemon.system.support.listener;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 系统 启动监听.
 *
 * @author lemon
 * @version 2018/2/9
 */
@Component
public class ApplicationReady implements ApplicationListener<ApplicationReadyEvent> {
    
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        System.out.println("\r");
        System.out.println("     ╭────╮         ╭───╮   ╭──── ────╮      ╭───╮       ╭──────╮               ");
        System.out.println("     ╰╮  ╭╯        /  ╭─╯   │    │    │     ╱     ╲      │       ╲              ");
        System.out.println("      │  │        /   ╰─╮   │ ─     ─ │    │       │     │   _   │              ");
        System.out.println("      │  │       │    ╭─╯   │   ╲_╱   │    │   O   │     │   │   │              ");
        System.out.println("      │  │       │    ╰─╮   │         │    │   │   │     │   │   │              ");
        System.out.println("     ╭╯  ╰────╮  │      │   │    │    │     ╲     ╱      │   │   │              ");
        System.out.println("     ╰────────╯  ╰──────╯   ╰────╯────╯      ╰───╯       ╰───┴───╯      v 1.0   ");
        System.out.println("\r");
        System.out.println("Welcome to use Lemon ");
    }
}
