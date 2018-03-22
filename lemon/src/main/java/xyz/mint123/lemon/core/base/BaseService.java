package xyz.mint123.lemon.core.base;

import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * service 基类
 * @author lemon
 * @version 2018/3/22
 */
public class BaseService<M> {

    @Autowired
    protected M mapper;


}
