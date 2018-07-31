package xyz.mint123.lemon.sys.web.vo;

import java.io.Serializable;

/**
 * 当前登录用户 信息
 */
public class UserVo implements Serializable {

    private static final long serialVersionUID = -6249390483592426549L;

    /**
     * 用户 id
     */
    private String id;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 用户 头像
     */
    private String avatar;
    /**
     * 通知数量
     */
    private Long notifyCount;

    public UserVo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getNotifyCount() {
        return notifyCount;
    }

    public void setNotifyCount(Long notifyCount) {
        this.notifyCount = notifyCount;
    }
}
