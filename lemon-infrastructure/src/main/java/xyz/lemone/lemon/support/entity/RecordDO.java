package xyz.lemone.lemon.support.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.time.Instant;

/**
 * 实体基类.
 *
 * @author lemon
 */
@MappedSuperclass
public class RecordDO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 创建时间.
     */
    @Column(name = "create_time")
    protected Instant createTime;
    
    /**
     * 修改时间.
     */
    @Column(name = "update_time")
    protected Instant updateTime;
    
    public RecordDO() {
    
    }
    
    public Instant getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }
    
    public Instant getUpdateTime() {
        return updateTime;
    }
    
    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("createTime", createTime).append("updateTime", updateTime).toString();
    }
}
