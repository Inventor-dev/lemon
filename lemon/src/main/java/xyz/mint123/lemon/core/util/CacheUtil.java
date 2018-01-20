package xyz.mint123.lemon.core.util;

import java.util.Collection;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import xyz.mint123.lemon.core.Constants;

/**
 * 缓存工具类
 * @author YP
 * @version 2018年1月20日
 */
public class CacheUtil {

	private volatile CacheManager cacheManager;
	
	public Collection<String> getCacheNames(){
		return cacheManager.getCacheNames();
	}
	
	public Cache getCache(){
		return cacheManager.getCache(Constants.CACHE_NAMESPACE);
	}
	
	private static CacheUtil that = new CacheUtil();
	
	public static CacheUtil getInstance() {
		
		if(that.cacheManager == null){
			that.cacheManager = SpringContextHolder.getBean(CacheManager.class);
		}
		return that;
	}
	
	private CacheUtil() {
		super();
	}
	
}
