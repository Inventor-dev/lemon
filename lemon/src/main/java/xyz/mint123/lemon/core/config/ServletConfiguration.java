package xyz.mint123.lemon.core.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * servlet 配置
 * @author lemon
 * @version 2018/1/22
 */
@Configuration
public class ServletConfiguration {

    /**
     * Druid 监控 servlet
     * @return
     */
    @Bean
    public ServletRegistrationBean druidStatViewServlet(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        registrationBean.addInitParameter("allow","127.0.0.1");
        registrationBean.addInitParameter("deny","192.0.0.1");
        registrationBean.addInitParameter("loginUsername","druid");
        registrationBean.addInitParameter("loginPassword","druid");
        registrationBean.addInitParameter("resetEnable","false");
        return registrationBean;
    }

    /**
     * Druid 监控 过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean druidStatFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new WebStatFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        registrationBean.addInitParameter("profileEnable","true");
        return registrationBean;

    }

}
