package xyz.mint123.lemon.config;

import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
/**
 * Druid  链接池配置
 * @author YP
 * @version 2018年1月18日
 */

@Configuration()
@ConfigurationProperties(value = "classpath:config/jdbc.properties", prefix = "db")
public class DruidConfiguration {
	
	//JDBC 驱动
	private String driver;
	
	//读取 url
	private String readUrl;
	
	//读取 账户
	private String readUserName;
	
	//读取 密码
	private String readPassword;
	
	//写入 账户
	private String writeUserName;
	
	//写入 密码
	private String writePassword;
	
	//初始链接数
	private int initialSize;
	
	//最大链接数
	private int maxActive;
	
	//最小链接数
	private int minIdle;
	
	//最小链接数
	private long maxWait;
	
	/**
	 * 读取数据源 
	 * @return
	 */
	@Bean(name="dataSource")
	public DataSource readDataSource(){
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(readUrl);
		dataSource.setUsername(readUserName);
		dataSource.setPassword(readPassword);
		dataSource.setInitialSize(initialSize);
		dataSource.setMaxActive(maxActive);
		dataSource.setMinIdle(minIdle);
		dataSource.setMaxWait(maxWait);
		dataSource.setDefaultReadOnly(true);
		return dataSource;
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
