package xyz.mint123.lemon.sys.util;

import java.util.List;
import java.util.ArrayList;

/**
 * 组织机构类型
 * @author lemon
 * @version 2018/1/23
 */
public enum OrganizationTypeEnum {
    /**
     * 根节点
     */
    ROOT("root","组织机构",false){
        @Override
        public List<OrganizationTypeEnum> getChildTypes() {
            ArrayList<OrganizationTypeEnum> childTypes = new ArrayList<>(2);
            childTypes.add(COMPANY);
            childTypes.add(DEPARTMENT);
            return childTypes;
        }
    },
    /**
     * 公司
     */
    COMPANY("com","公司",true) {
        @Override
        public List<OrganizationTypeEnum> getChildTypes() {
            ArrayList<OrganizationTypeEnum> childTypes = new ArrayList<>(3);
            childTypes.add(COMPANY);
            childTypes.add(DEPARTMENT);
            childTypes.add(POST);
            return childTypes;
        }
    },
    /**
     * 部门
     */
    DEPARTMENT("dept","部门",true) {
        @Override
        public List<OrganizationTypeEnum> getChildTypes() {
            ArrayList<OrganizationTypeEnum> childTypes = new ArrayList<>(1);
            childTypes.add(POST);
            return childTypes;
        }
    },
    /**
     * 组织机构类型 岗位
     */
    POST("post","岗位",true) {
        @Override
        public  List<OrganizationTypeEnum> getChildTypes() {
            ArrayList<OrganizationTypeEnum> childTypes = new ArrayList<>(1);
            childTypes.add(PERSONNEL);
            return childTypes;
        }
    },
    /**
     * 人员
     */
    PERSONNEL("user","人员",true) {
        @Override
        public List<OrganizationTypeEnum> getChildTypes() {
            return null;
        }
    };

    /**
     * 类型值
     */
    final String value;
    /**
     * 类型名称
     */
    final String name;
    /**
     * 允许添加 当前类型
     */
    final Boolean allowAdd;

    OrganizationTypeEnum(String value, String name, Boolean allowAdd) {
        this.value = value;
        this.name = name;
        this.allowAdd = allowAdd;
    }

    /**
     * 获取 子节点 类型
     * @return 子节点类型 集合
     */
    public abstract List<OrganizationTypeEnum>  getChildTypes();

    /**
     * 通过 类型值 获取 类型枚举
     * @param value 类型值
     * @return 类型枚举
     */
    public static OrganizationTypeEnum getTypeEnum(String value){

        for ( OrganizationTypeEnum type : OrganizationTypeEnum.values()) {
            if(type.value.equals(value)){
                return type;
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return this.value;
    }
}
