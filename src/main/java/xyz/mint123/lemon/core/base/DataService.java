package xyz.mint123.lemon.core.base;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.io.Serializable;

/**
 *  数据记录 service 基类
 * @author lemon
 * @version 2018/3/22
 */
public class DataService<M extends DataMapper<D,DI>,D extends DataEntity<DI>,DI extends Serializable> extends ServiceImpl<M,D> {




}
