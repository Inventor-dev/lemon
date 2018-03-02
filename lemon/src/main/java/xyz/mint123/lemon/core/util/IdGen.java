package xyz.mint123.lemon.core.util;

import java.util.UUID;

/**
 * 主键生成器
 * @author lemon
 * @version 2018年1月19日
 */
public class IdGen {

	
	public static String getId() {
		return UUID.randomUUID().toString();
	}

}
