package xyz.mint123.lemon.core.base;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础 entity
 * @author lemon
 * @version 2018/1/23
 *
 */
public class BaseEntity<T extends Serializable> extends DataEntity {

    private static final long serialVersionUID = 1L;

    private T id;

    public BaseEntity() {
    }

    public BaseEntity(T id) {
        super();
        this.id = id;
    }

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }
}
