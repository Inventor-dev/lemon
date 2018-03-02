package xyz.mint123.lemon.core.support.datasource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import xyz.mint123.lemon.core.base.Parameter;

/***
 * 数据源切面
 * @author lemon
 * @version 2018年1月19日
 */
@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DataSourceAspect {
	private final Logger logger = LogManager.getLogger();

	@Pointcut("this(xyz.mint123.lemon.core.base.BaseProviderImpl)")
	public void aspect() {
	}

	static {
		System.setProperty("druid.logType", "log4j2");
	}

	/**
	 * 配置前置通知,使用在方法aspect()上注册的切入点
	 */
	@Before("aspect()")
	public void before(JoinPoint point) {
		Parameter parameter = (Parameter) point.getArgs()[0];
		String method = parameter.getMethod();
		try {
			L: for (String key : RoutingDataSource.METHODTYPE.keySet()) {
				for (String type : RoutingDataSource.METHODTYPE.get(key)) {
					if (method.startsWith(type)) {
						logger.info(key);
						DataSourceHandle.putDataSource(key);
						break L;
					}
				}
			}
		} catch (Exception e) {
			logger.error("", e);
			DataSourceHandle.putDataSource("write");
		}
	}

	@After("aspect()")
	public void after(JoinPoint point) {
		DataSourceHandle.clear();
	}
}
