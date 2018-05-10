package xyz.mint123.lemon.core.base;

import com.baomidou.mybatisplus.annotations.TableId;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础 entity
 * @param <I> 主键类型
 * @author lemon
 * @version 2018/1/23
 */
public class BaseEntity<I extends Serializable> extends DataEntity<String> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id")
    private I id;

    public BaseEntity() {
    }

    public BaseEntity(I id) {
        super();
        this.id = id;
    }

    public I getId() {
        return id;
    }

    public void setId(I id) {
        this.id = id;
    }
}
