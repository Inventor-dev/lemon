package xyz.lemone.lemon.system.shiro.param;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Username Password Login mode parameters.
 *
 * @author lemon
 */
public class UsernamePasswordLoginParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The username.
     */
    @NotBlank
    private String username;

    /**
     * The password.
     */
    @NotBlank
    private String password;

    public UsernamePasswordLoginParam() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("username", username)
                .append("password", password)
                .toString();
    }

}
