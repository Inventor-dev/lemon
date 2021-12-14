package xyz.lemone.lemon.common.base.entity;

import cn.org.atool.fluent.mybatis.annotation.LogicDelete;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 逻辑删除基类.
 *
 * @author lemon
 */
public class LogicDeleteDO extends RecordDO {

    private static final long serialVersionUID = 1L;

    /**
     * 逻辑删除标志.
     */
    @LogicDelete
    protected Integer deleteFlag;

    public LogicDeleteDO() {
        super();
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
