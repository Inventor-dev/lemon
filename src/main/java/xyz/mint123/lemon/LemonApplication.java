package xyz.mint123.lemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import xyz.mint123.lemon.core.util.SpringUtils;

/**
 * @author lemon
 * @version 2018-1-22
 */
@Import(SpringUtils.class)
@SpringBootApplication
public class LemonApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(LemonApplication.class);
    }



	public static void main(String[] args) {
        SpringApplication.run(LemonApplication.class, args);
	}






}


