package xyz.mint123.lemon.core.dataSource;
/**
 * 
 * @author YP
 * @version 2018年1月19日
 */
public class DataSourceHandle {

		// 数据源名称线程上下文
		private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

		public static void putDataSource(String datasource) {
			contextHolder.set(datasource);
		}

		public static String getDataSource() {
			return contextHolder.get();
		}

		public static void clear() {
			contextHolder.remove();
		}
	
	
}
