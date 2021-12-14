package xyz.lemone.lemon.system.organization.entity;

import java.io.Serializable;

/**
 * SYS 组织机构 .
 *
 * @author lemon
 * @version 2018/1/23
 */
public class Organization implements Serializable {

    private static final long serialVersionUID = 3067846934925263575L;

    /**
     * 机构ID.
     */
    private String id;

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
     * 上级机构.
     */
    private Organization parent;

}
