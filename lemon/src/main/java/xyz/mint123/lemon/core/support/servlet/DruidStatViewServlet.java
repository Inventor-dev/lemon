package xyz.mint123.lemon.core.support.servlet;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * @author lemon
 * @version 2018/1/22
 */
@WebServlet(urlPatterns = "/druid2/*",
        initParams = {
                @WebInitParam(name = "allow", value = "192.168.1.72,127.0.0.1"),
                @WebInitParam(name="deny",value="192.0.0.1"),
                @WebInitParam(name="loginUsername",value="druid"),
                @WebInitParam(name="loginPassword",value="druid"),
                @WebInitParam(name="resetEnable",value="false")
        }
)
public class DruidStatViewServlet extends StatViewServlet {
    private static final long serialVersionUID = 1L;
}
