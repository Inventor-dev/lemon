package xyz.mint123.lemon.core.base;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.util.annotation.Nullable;

import java.io.Serializable;

/**
 * service 基类
 * @param <M> mapper类型
 * @param <E> 实体类型
 * @param <I> 主键类型
 * @author lemon
 * @version 2018/3/22
 */
public class BaseService<M extends BaseMapper<E,I>,E extends BaseEntity<I>,I extends Serializable> extends DataService<M,E,String> {




}
