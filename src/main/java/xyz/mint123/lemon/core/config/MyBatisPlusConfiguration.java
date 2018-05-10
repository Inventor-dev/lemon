package xyz.mint123.lemon.core.config;


import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.mint123.lemon.core.support.mybatisplus.MetaObjectHandler;

/**
 * mybatisplus 配置
 * @author Mint
 */
@Configuration
public class MyBatisPlusConfiguration {

    /**
     * 分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }


    /**
     * 公共字段填充
     * <br/> 推荐 spring 注入方式 否则会出现类不能加载问题
     */
    @Bean
    public MetaObjectHandler metaObjectHandler(){
        return new MetaObjectHandler();
    }





}
