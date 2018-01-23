package xyz.mint123.lemon.core.base;

import java.io.Serializable;

/**
 * ID  实体
 * @author lemon
 * @version 2018/1/22
 */
public class IDEntity<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private T id;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public IDEntity() {
        super();
    }

    public IDEntity(T id) {
        super();
        this.id = id;
    }

    /**
     * equals <br/>
     * 非同一个指针时会比对对象的 类型 、id
     * @param obj
     * @return
     */
    public boolean equalsId(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        IDEntity<?> that = (IDEntity<?>) obj;
        return null == this.getId() ? false : this.getId().equals(that.getId());

    }


}
