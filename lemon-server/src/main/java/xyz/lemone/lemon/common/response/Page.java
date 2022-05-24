package xyz.lemone.lemon.common.response;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 分页数据.
 *
 * @author lemon
 */
public class Page implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页.
     */
    private Integer current;

    /**
     * 每页数据条数.
     */
    private Integer pageSize;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("current", current)
                .append("pageSize", pageSize)
                .toString();
    }
}
