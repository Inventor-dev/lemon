package xyz.lemone.lemon.support.entity;

import jakarta.persistence.Column;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 操作记录实体基类.
 *
 * @author lemon
 */
public class OperatedRecordDO extends RecordDO {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 创建人.
     */
    @Column(name = "create_by")
    protected String createBy;
    
    /**
     * 修改人.
     */
    @Column(name = "update_by")
    protected String updateBy;
    
    public OperatedRecordDO() {
    }
    
    public String getCreateBy() {
        return createBy;
    }
    
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    
    public String getUpdateBy() {
        return updateBy;
    }
    
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("createBy", createBy).append("updateBy", updateBy)
                .append("createTime", createTime).append("updateTime", updateTime).toString();
    }
}
