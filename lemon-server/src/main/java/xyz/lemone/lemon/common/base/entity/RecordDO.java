package xyz.lemone.lemon.common.base.entity;

import cn.org.atool.fluent.mybatis.annotation.TableField;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 实体基类.
 *
 * @author lemon
 */
public class RecordDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 创建时间.
     */
    @TableField("create_time")
    protected LocalDateTime createTime;

    /**
     * 修改时间.
     */
    @TableField("update_time")
    protected LocalDateTime updateTime;

    public RecordDO() {
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
