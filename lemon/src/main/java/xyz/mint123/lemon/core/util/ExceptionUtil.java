package xyz.mint123.lemon.core.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常工具类
 * @author lemon
 * @version 2018-1-22
 */
public class ExceptionUtil {

	
	/**
	 * 将ErrorStack转化为String.
	 */
	public static String getStackTraceAsString(Throwable e) {
		if (e == null) {
			return "";
		}
		StringWriter stringWriter = new StringWriter();
		e.printStackTrace(new PrintWriter(stringWriter));
		return stringWriter.toString();
	}
	
}
