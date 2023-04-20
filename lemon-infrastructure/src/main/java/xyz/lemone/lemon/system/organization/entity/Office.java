package xyz.lemone.lemon.system.organization.entity;

/**
 * SYS 模块 公司部门 实体 .
 *
 * @author lemon
 * @version 2018/1/23
 */
public class Office {

    private static final long serialVersionUID = -6375781200650863256L;

    /**
     * 机构名称.
     */
    private String name;

    /**
     * 机构编码.
     */
    private String code;

    /**
     * 机构类型.
     */
    private String type;

    /**
     * 机构上级.
     */
    private Office parent;

    public Office() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Office getParent() {
        return parent;
    }

    public void setParent(Office parent) {
        this.parent = parent;
    }
}
