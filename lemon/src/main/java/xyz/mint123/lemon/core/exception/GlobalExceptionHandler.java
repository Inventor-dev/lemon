package xyz.mint123.lemon.core.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常代理类
 * @author lemon
 * @version 2018/2/28
 */

@EnableWebMvc
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler()
    public @ResponseBody Object errorInfo(HttpServletRequest request, Exception e){

       return null;
    }


}
