package xyz.mint123.lemon.core.support.servlet;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * @author lemon
 * @version 2018/1/22
 */
@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*",
        initParams = {
                @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid2/*")
        }
)
public class DruidStatFilter extends WebStatFilter {
}
