package xyz.lemone.lemon.system;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

/**
 * R2dbcConfig.
 * @author lemon
 */
@Configuration
@EnableR2dbcRepositories(basePackages = "xyz.lemone.lemon.system.**.repository")
public class R2dbcConfig {

}
