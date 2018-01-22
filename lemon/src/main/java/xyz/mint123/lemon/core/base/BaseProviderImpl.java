package xyz.mint123.lemon.core.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import com.alibaba.fastjson.JSON;

import xyz.mint123.lemon.core.Constants;
import xyz.mint123.lemon.core.util.ExceptionUtil;
import xyz.mint123.lemon.core.util.ReflectUtil;

/**
 * @author lemon
 * @version  2018-1-22
 */
public class BaseProviderImpl implements ApplicationContextAware, BaseProvider {
	 protected Logger logger = LogManager.getLogger();
	    private ApplicationContext applicationContext;

	    @Override
	    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
	        this.applicationContext = applicationContext;
	    }
		@Override
	    public Parameter execute(Parameter parameter) {
	        String no = parameter.getNo();
	        logger.info("{} request：{}", no, JSON.toJSONString(parameter));
	        Object service = applicationContext.getBean(parameter.getService());
	        try {
	            String method = parameter.getMethod();
	            Object[] param = parameter.getParam();
	            Object result = ReflectUtil.invokeMethod(service, method, param);
	            Parameter response = new Parameter(result);
	            logger.info("{} response：{}", no, JSON.toJSONString(response));
	            return response;
	        } catch (Exception e) {
	            String msg = ExceptionUtil.getStackTraceAsString(e);
	            logger.error(no + " " + Constants.EXCEPTION_HEAD + msg, e);
	            throw e;
	        }
	    }
}
