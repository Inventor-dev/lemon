package xyz.mint123.lemon.core.support.result.ant;

import java.io.Serializable;

/**
 * Ant Design  分页
 */
public class Pagination implements Serializable {

    private static final long serialVersionUID = 856162738653444490L;

    /**
     * 每页显示条数
     */
    private int size ;

    /**
     * 总数据量
     */
    private long total;

    /**
     * 当前页
     */
    private int current ;


    public Pagination() {
    }

    public Pagination(int current, int size, long total) {
        this.size = size;
        this.total = total;
        this.current = current;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }
}
