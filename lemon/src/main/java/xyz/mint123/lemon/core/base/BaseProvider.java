package xyz.mint123.lemon.core.base;

/**
 * @author lemon
 * @version 2018-1-22
 */
public interface BaseProvider {
    /**
     * 数据源 代理
     * @param parameter
     * @return
     */
	Parameter execute(Parameter parameter);
}
