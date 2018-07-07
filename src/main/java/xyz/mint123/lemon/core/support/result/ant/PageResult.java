package xyz.mint123.lemon.core.support.result.ant;

import com.baomidou.mybatisplus.plugins.Page;
import xyz.mint123.lemon.core.support.result.JsonResult;

import java.util.List;

/**
 * Ant Design page result
 *
 * @param <E>
 */
public class PageResult<E> extends JsonResult<Page<E>> {
    private static final long serialVersionUID = 7271395773499495345L;

    public PageResult() {
    }

    public PageResult(Page<E> page) {
        super();
        super.data = page;
    }


    public List<E> getList() {
        return data.getRecords();
    }


    public Pagination getPagination() {
        return new Pagination(data.getCurrent(), data.getSize(), data.getTotal());
    }


}
