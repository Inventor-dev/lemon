package xyz.lemone.lemon.system.support.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis 相关配置.
 * @author lime
 */
@Configuration
@MapperScan("xyz.lemone.lemon.*.mapper.*")
public class FluentMybatisConfig {
}
