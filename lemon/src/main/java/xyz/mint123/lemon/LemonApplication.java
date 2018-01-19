package xyz.mint123.lemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import xyz.mint123.lemon.core.config.DataSourceConfiguration;

@SpringBootApplication
@EnableConfigurationProperties(DataSourceConfiguration.class)
public class LemonApplication {

	public static void main(String[] args) {
		System.out.printf("\r\r");
		System.out.println("                      .-~~~~~~~~~-._       _.-~~~~~~~~~-.                         "); 
		System.out.println("                  __.'              ~.   .~              `.__                     "); 
		System.out.println("                .'//     SERVER       \\./       START      \\\\`.                "); 
		System.out.println("              .'//                     |                     \\`.                 "); 
		System.out.println("            .'// .-~\"\"\"\"\"\"\"~~~~-._     |     _,-~~~~\"\"\"\"\"\"\"~-. \\`. "); 
		System.out.println("          .'//.-\"                 `-.  |  .-'                 \"-.\\\\`.         "); 
		System.out.println("        .'//______.============-..   \\ | /   ..-============.______\\\\`.        "); 
		System.out.println("      .'______________________________\\|/______________________________`.        ");
		System.out.println("");
		System.out.printf("\t\t     ╭────╮                  ╭──╮      ╭──── ────╮           ╭──╮             ╭────╮                                   \r ");
		System.out.printf("\t\t     ╰╮    ╭╯               / ╭─╯     │        │        │        /   \\   │           \\          \r ");
		System.out.printf("\t\t      │    │              /  ╰─╮     │    ─      ─    │      │               │       │      _  │                             \r ");
		System.out.printf("\t\t      │    │            │        ╭─╯     │     \\_/  │      │      O  │       │       │     │                          \r ");
		System.out.printf("\t\t      │    │            │        ╰─╮     │        │        │       │              │       │       │      │                            \r ");
		System.out.printf("\t\t     ╭╯    ╰────╮  │            │     │        │        │       \\    /   │      │      │                            \r ");
		System.out.printf("\t\t     ╰────────╯  ╰──────╯     ╰────╯────╯           ╰───╯           ╰───┴───╯          v 1.0  \r ");
		
		SpringApplication.run(LemonApplication.class, args);
	}
}
