package xyz.lemone.lemon.common.response;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Collection;

/**
 * 分页数据.
 *
 * @author lemon
 */
public class Page<E> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页.
     */
    private Integer current;

    /**
     * 每页数据条数.
     */
    private Integer pageSize;

    /**
     * 数据.
     */
    private Collection<E> list;

    /**
     * 数据总条数.
     */
    private Long total;

    public Page() {
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Collection<E> getList() {
        return list;
    }

    public void setList(Collection<E> list) {
        this.list = list;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("current", current)
                .append("pageSize", pageSize)
                .append("list", list)
                .append("total", total)
                .toString();
    }
}
