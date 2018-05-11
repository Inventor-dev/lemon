package xyz.mint123.lemon.core.config;


import com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.spring.boot.starter.MybatisPlusAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.mint123.lemon.core.support.mybatisplus.MetaObjectHandler;

/**
 * mybatisplus 配置
 * @author Mint
 */
@Configuration
@ConditionalOnBean(MybatisPlusAutoConfiguration.class)
public class MyBatisPlusConfiguration {

    /**
     * 公共字段填充
     * <br/> 推荐 spring 注入方式 否则会出现类不能加载问题
     */
    @Bean
    public MetaObjectHandler metaObjectHandler(){
        return new MetaObjectHandler();
    }

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 乐观锁插件
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }



}
