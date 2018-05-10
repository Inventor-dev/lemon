package xyz.mint123.lemon.core.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.Version;
import com.baomidou.mybatisplus.enums.FieldFill;

import java.io.Serializable;
import java.util.Date;

/**
 *   数据基类
 *  @param <DI> 数据记录ID 类型
 *  @author lemon
 *  @version 2018/3/22
 *
 */
public class DataEntity<DI extends Serializable> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 创建者
     */
    @TableField(fill = FieldFill.INSERT)
    private DI creator;

    /**
     * 创建日期
     */
    @TableField(value = "date_created",fill = FieldFill.INSERT)
    private Date dateCreated;

    /**
     * 创建者
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private DI mender;

    /**
     * 修改日期
     */
    @TableField(value = "date_modified",fill = FieldFill.INSERT_UPDATE)
    private Date dateModified;

    /**
     * 版本
     */
    @Version
    private Long version;

    /**
     * 数据状态
     * 启用 @Link Constants.DATA_FLAG_NORMAL
     * 删除 @Link Constants.DATA_FLAG_DELETE
     * 临时 @Link Constants.DATA_FLAG_TEMP
     */
    @TableLogic
    @TableField("data_flag")
    private Integer dataFlag;

    /**
     * 备注信息
     */
    private String remarks;

    public DataEntity() {
    }

    public DI getCreator() {
        return creator;
    }

    public void setCreator(DI creator) {
        this.creator = creator;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public DI getMender() {
        return mender;
    }

    public void setMender(DI mender) {
        this.mender = mender;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getDataFlag() {
        return dataFlag;
    }

    public void setDataFlag(Integer dataFlag) {
        this.dataFlag = dataFlag;
    }
}
