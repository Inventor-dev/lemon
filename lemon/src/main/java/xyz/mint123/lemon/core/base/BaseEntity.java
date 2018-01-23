package xyz.mint123.lemon.core.base;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础 entity
 * @author lemon
 * @version 2018/1/23
 * TODO 创建者,修改者 换为 机构记录
 */
public class BaseEntity<T extends Serializable> extends IDEntity<T> {

    private static final long serialVersionUID = 1L;
    /**
     * 创建者
     */
    private String creator;

    /**
     * 创建日期
     */
    private Date dateCreated;

    /**
     * 创建者
     */
    private String mender;

    /**
     * 修改日期
     */
    private Date dateModified;

    /**
     * 版本
     */
    private Long version;

    /**
     * 备注信息
     */
    private String remarks;


    public BaseEntity() {
    }

    public BaseEntity(T id) {
        super(id);
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getMender() {
        return mender;
    }

    public void setMender(String mender) {
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
}
