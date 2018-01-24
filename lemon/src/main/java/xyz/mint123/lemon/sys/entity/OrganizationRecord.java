package xyz.mint123.lemon.sys.entity;

import xyz.mint123.lemon.core.base.BaseEntity;

/**
 * SYS 人员组织机构记录
 * @author lemon
 * @version 2018/1/23
 */
public class OrganizationRecord extends BaseEntity<String> {

    private static final long serialVersionUID = -6217085555697682213L;

    /**
     * 人员 ID
     */
    private String userId;
    /**
     * 人员名称
     */
    private String name;
    /**
     * 人员 编码
     */
    private String code;
    /**
     * 人员 所属公司ID
     */
    private String companyId;
    /**
     * 人员 所属公司名称
     */
    private String companyName;
    /**
     * 人员 所属部门ID
     */
    private String departmentId;
    /**
     * 人员 所属部门名称
     */
    private String departmentName;
    /**
     * 人员 所属岗位ID
     */
    private String postId;
    /**
     * 人员 所属岗位名称
     */
    private String postName;
    /**
     * 全路径ID
     */
    private String fullId;
    /**
     * 全路径名称
     */
    private String fullName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getFullId() {
        return fullId;
    }

    public void setFullId(String fullId) {
        this.fullId = fullId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
