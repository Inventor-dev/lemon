package xyz.mint123.lemon.core.config;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import xyz.mint123.lemon.core.datasource.RoutingDataSource;

import javax.sql.DataSource;

/**
 * Druid  链接池配置
 * @author YP
 * @version 2018年1月18日
 */

@Configuration
@ConfigurationProperties(prefix = "db")
public class DataSourceConfiguration {

	/**
	 * JDBC 驱动
	 */
	private String driver;
	/**
	 * 读取 url
	 */
	private String readUrl;

	/**
	 * 读取 账户
	 */
	private String readUserName;

	/**
	 * 读取 密码
	 */
	private String readPassword;

	/**
	 * 读取密码公钥
	 */
	private String readKey;

	/**
	 * 写入 url
	 */
	private String writeUrl;

    /**
     * 写入 账户
     */
	private String writeUserName;

	/**
	 * 写入 密码
	 */
	private String writePassword;

	/**
	 * 写入密码公钥
	 */
	private String writeKey;

	/**
	 * 初始链接数
	 */
	private int initialSize;

	/**
	 * 最大链接数
	 */
	private int maxActive;

	/**
	 * 最小链接数
	 */
	private int minIdle;

	/**
	 * 最长等待时间
	 */
	private long maxWait;
	
	
	/**
	 * 状态过滤器
	 */
	@Bean(name="statFilter")
	public Filter statFilter (){
		StatFilter statFilter = new StatFilter();
		statFilter.setSlowSqlMillis(3000);
		statFilter.setLogSlowSql(true);
		statFilter.setMergeSql(true);
		return statFilter;
	}
	
	
	/**
	 * 读取数据源 
	 * @return
	 * @throws SQLException 
	 */
	@Bean(name="readDataSource",initMethod="init")
	public DruidDataSource readDataSource(@Autowired Filter statFilter) throws SQLException{
		DruidDataSource readDataSource = new DruidDataSource();
		readDataSource.setDriverClassName(driver);
		readDataSource.setUrl(readUrl);
		readDataSource.setUsername(readUserName);
		readDataSource.setPassword(readPassword);
		readDataSource.setInitialSize(initialSize);
		readDataSource.setMaxActive(maxActive);
		readDataSource.setMinIdle(minIdle);
		readDataSource.setMaxWait(maxWait);
		readDataSource.setDefaultReadOnly(true);
		ArrayList<Filter> filters = new ArrayList<Filter>();
		filters.add(statFilter);
		readDataSource.setProxyFilters(filters);
		readDataSource.setFilters("config");
		readDataSource.setConnectionProperties("config.decrypt=true;config.decrypt.key=" + readKey);
		return readDataSource;
	}

	
	/**
	 * 写入数据源 
	 * @return
	 * @throws SQLException 
	 */
	@Bean(name="writeDataSource",initMethod="init")
	public DruidDataSource writeDataSource(@Autowired Filter statFilter) throws SQLException{
		DruidDataSource writeDataSource = new DruidDataSource();
		writeDataSource.setDriverClassName(driver);
		writeDataSource.setUrl(writeUrl);
		writeDataSource.setUsername(writeUserName);
		writeDataSource.setPassword(writePassword);
		writeDataSource.setInitialSize(initialSize);
		writeDataSource.setMaxActive(maxActive);
		writeDataSource.setMinIdle(minIdle);
		writeDataSource.setMaxWait(maxWait);
		writeDataSource.setDefaultReadOnly(false);
		ArrayList<Filter> filters = new ArrayList<Filter>();
		filters.add(statFilter);
		writeDataSource.setFilters("config");
		writeDataSource.setConnectionProperties("config.decrypt=true;config.decrypt.key=" + writeKey);
		return writeDataSource;
	}


	/**
	 * 真实 数据源在
	 * @param readDataSource   读取数据源
	 * @param writeDataSource  写入数据源
	 * @return
	 */
	@Bean(name="dataSource")
	public DataSource dataSource(@Autowired DataSource readDataSource, @Autowired DataSource writeDataSource){
		RoutingDataSource dataSource = new RoutingDataSource();
		Map<Object, Object> dataSourceMap = new HashMap<Object, Object>(2);
		dataSourceMap.put("read", readDataSource);
		dataSourceMap.put("write", writeDataSource);
		dataSource.setTargetDataSources(dataSourceMap);
		dataSource.setDefaultTargetDataSource(readDataSource);
		HashMap<String, String> methodTypeMap = new HashMap<String, String>(2);
		methodTypeMap.put("read", ",get,select,count,list,query,");
		methodTypeMap.put("write", ",add,insert,create,update,delete,remove,");
		dataSource.setMethodType(methodTypeMap);
		return dataSource;
	}

    public SqlSessionFactoryBean sqlSessionFactoryBean(){
		return  null;
	}



	public String getDriver() {
		return driver;
	}


	public void setDriver(String driver) {
		this.driver = driver;
	}


	public String getReadUrl() {
		return readUrl;
	}


	public void setReadUrl(String readUrl) {
		this.readUrl = readUrl;
	}


	public String getReadUserName() {
		return readUserName;
	}


	public void setReadUserName(String readUserName) {
		this.readUserName = readUserName;
	}


	public String getReadPassword() {
		return readPassword;
	}


	public void setReadPassword(String readPassword) {
		this.readPassword = readPassword;
	}


	public String getReadKey() {
		return readKey;
	}


	public void setReadKey(String readKey) {
		this.readKey = readKey;
	}


	public String getWriteUrl() {
		return writeUrl;
	}


	public void setWriteUrl(String writeUrl) {
		this.writeUrl = writeUrl;
	}


	public String getWriteUserName() {
		return writeUserName;
	}


	public void setWriteUserName(String writeUserName) {
		this.writeUserName = writeUserName;
	}


	public String getWritePassword() {
		return writePassword;
	}


	public void setWritePassword(String writePassword) {
		this.writePassword = writePassword;
	}


	public String getWriteKey() {
		return writeKey;
	}


	public void setWriteKey(String writeKey) {
		this.writeKey = writeKey;
	}


	public int getInitialSize() {
		return initialSize;
	}


	public void setInitialSize(int initialSize) {
		this.initialSize = initialSize;
	}


	public int getMaxActive() {
		return maxActive;
	}


	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}


	public int getMinIdle() {
		return minIdle;
	}


	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}


	public long getMaxWait() {
		return maxWait;
	}


	public void setMaxWait(long maxWait) {
		this.maxWait = maxWait;
	}
	

	


	
	
	
}
