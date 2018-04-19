package xyz.mint123.lemon.core.base;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.util.annotation.Nullable;

/**
 * service 基类
 * @author lemon
 * @version 2018/3/22
 */
public class BaseService<E extends DataEntity,M extends BaseMapper<E>> extends ServiceImpl<M,E> {




}
