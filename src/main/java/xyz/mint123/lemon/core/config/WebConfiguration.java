package xyz.mint123.lemon.core.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * WEB 配置
 * @author Mint
 */
@EnableWebFlux
@Configurable
@ConditionalOnBean(WebFluxConfigurer.class)
public class WebConfiguration {




}
