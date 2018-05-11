package xyz.mint123.lemon.core.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Druid  数据源 配置
 * @author lemon
 * @version 2018年1月18日
 */
@Configuration
@ConditionalOnBean(DruidDataSourceAutoConfigure.class)
@EnableTransactionManagement
public class DruidConfiguration {




}
