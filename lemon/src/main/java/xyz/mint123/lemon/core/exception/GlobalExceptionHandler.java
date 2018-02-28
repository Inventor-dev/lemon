package xyz.mint123.lemon.core.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import xyz.mint123.lemon.core.result.ErrorResult;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常代理类
 * @author lemon
 * @version 2018/2/28
 */

@EnableWebMvc
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public @ResponseBody ErrorResult errorInfo(HttpServletRequest request, Exception e){
        ErrorResult result = new ErrorResult();
        result.setCode("500");
        result.setMsg(e.getMessage());
        result.setUri(request.getRequestURI());
        return result;
    }


}
