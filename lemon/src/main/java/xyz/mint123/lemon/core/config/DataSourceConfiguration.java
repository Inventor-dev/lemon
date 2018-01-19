package xyz.mint123.lemon.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * 数据源 配置
 * @author YP
 * @version 2018年1月19日
 */
@Configuration
@ConfigurationProperties
public class DataSourceConfiguration {
//
//	@Bean(name="dataSource")
//	public RoutingDataSource dataSource(){
//		DataSource readDataSource = SpringContextHolder.getBean("readDataSource");
//		DataSource writeDataSource = SpringContextHolder.getBean("writeDataSource");
//		RoutingDataSource dataSource = new RoutingDataSource();
//		Map<Object, Object> dataSourceMap = new HashMap<Object, Object>();
//		dataSourceMap.put("read", readDataSource);
//		dataSourceMap.put("write", writeDataSource);
//		dataSource.setTargetDataSources(dataSourceMap);
//		dataSource.setDefaultTargetDataSource(readDataSource);
//		HashMap<String, String> methodTypeMap = new HashMap<String, String>();
//		methodTypeMap.put("read", ",get,select,count,list,query,");
//		methodTypeMap.put("write", ",add,insert,create,update,delete,remove,");
//		dataSource.setMethodType(methodTypeMap);
//		return dataSource;
//	}
//	
	
	
}
