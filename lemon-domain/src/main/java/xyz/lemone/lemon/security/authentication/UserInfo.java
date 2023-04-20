package xyz.lemone.lemon.security.authentication;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 认证后用户信息.
 *
 * @author lemon
 * @version 2020/10/03
 */
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户唯一标识.
     */
    private String userId;

    /**
     * 登陆名.
     */
    private String username;

    /**
     * 手机号.
     */
    private String phone;

    /**
     * 人员 编码.
     */
    private String userCode;

    /**
     * 真实姓名.
     */
    private String realName;

    private UserInfo() {
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

    public String getUserCode() {
        return userCode;
    }

    public String getRealName() {
        return realName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("userId", userId)
                .append("username", username)
                .append("phone", phone)
                .append("userCode", userCode)
                .append("realName", realName)
                .toString();
    }

    public static final class UserInfoBuilder {
        private UserInfo userInfo;

        private UserInfoBuilder() {
            userInfo = new UserInfo();
        }

        public static UserInfoBuilder anUserInfo() {
            return new UserInfoBuilder();
        }

        public UserInfoBuilder withUserId(String userId) {
            userInfo.userId = userId;
            return this;
        }

        public UserInfoBuilder withUsername(String username) {
            userInfo.username = username;
            return this;
        }

        public UserInfoBuilder withPhone(String phone) {
            userInfo.phone = phone;
            return this;
        }

        public UserInfoBuilder withUserCode(String userCode) {
            userInfo.userCode = userCode;
            return this;
        }

        public UserInfoBuilder withRealName(String realName) {
            userInfo.realName = realName;
            return this;
        }

        public UserInfo build() {
            return userInfo;
        }
    }
}
