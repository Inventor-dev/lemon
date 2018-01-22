package xyz.mint123.lemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * @author lemon
 * @version 2018-1-22
 */
@SpringBootApplication
public class LemonApplication {

	public static void main(String[] args) {
        System.out.println("");
        System.out.println("");
        System.out.println("     ╭────╮         ╭───╮   ╭──── ────╮      ╭───╮       ╭──────╮               ");
        System.out.println("     ╰╮  ╭╯        /  ╭─╯   │    │    │     /     \\      │       \\          	");
        System.out.println("      │  │        /   ╰─╮   │ ─     ─ │    │       │     │   _   │              ");
        System.out.println("      │  │       │    ╭─╯   │   \\_/   │    │   O   │     │   │   │             ");
        System.out.println("      │  │       │    ╰─╮   │         │    │   │   │     │   │   │              ");
        System.out.println("     ╭╯  ╰────╮  │      │   │    │    │     \\     /      │   │   │             ");
        System.out.println("     ╰────────╯  ╰──────╯   ╰────╯────╯      ╰───╯       ╰───┴───╯      v 1.0   ");
        System.out.println("");
        System.out.println("");

        SpringApplication.run(LemonApplication.class, args);
	}
}


