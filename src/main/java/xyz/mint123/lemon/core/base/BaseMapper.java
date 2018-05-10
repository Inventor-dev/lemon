package xyz.mint123.lemon.core.base;

import java.io.Serializable;

/**
 * base mapper
 * @param <E> 基类类型
 * @param <I> 基类ID类型
 * @author lemon
 * @version 2018/3/23
 */
public interface BaseMapper<E extends BaseEntity<I>,I extends Serializable> extends DataMapper<E,String> {


}
