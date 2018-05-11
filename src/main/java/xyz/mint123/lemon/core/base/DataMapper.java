package xyz.mint123.lemon.core.base;

import java.io.Serializable;

/**
 * 数据 mapper
 * @param <D> 实体类型
 * @param <DI> 记录ID 类型
 * @author lemon
 */
public interface DataMapper<D extends DataEntity<DI>, DI extends Serializable> extends com.baomidou.mybatisplus.mapper.BaseMapper<D> {

}
