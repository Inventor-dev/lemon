package xyz.mint123.lemon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import javax.servlet.ServletException;

/**
 * @author lemon
 * @version 2018-1-22
 */
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

