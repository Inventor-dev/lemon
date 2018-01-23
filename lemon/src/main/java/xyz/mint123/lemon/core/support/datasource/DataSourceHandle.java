package xyz.mint123.lemon.core.support.datasource;
/**
 * 
 * @author YP
 * @version 2018年1月19日
 */
public class DataSourceHandle {

	/**
	 * 数据源名称线程上下文
	 */
	private static final ThreadLocal<String>  CONTEXT_HOLDER  = new ThreadLocal<String>();

		public static void putDataSource(String datasource) {
			CONTEXT_HOLDER.set(datasource);
		}

		public static String getDataSource() {
			return CONTEXT_HOLDER.get();
		}

		public static void clear() {
			CONTEXT_HOLDER.remove();
		}
	
	
}
