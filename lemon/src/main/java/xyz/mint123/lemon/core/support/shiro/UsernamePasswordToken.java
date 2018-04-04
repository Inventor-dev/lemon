package xyz.mint123.lemon.core.support.shiro;

/**
 * 登陆令牌
 * @author lemon
 * @version 2018/3/28
 */
public class UsernamePasswordToken extends org.apache.shiro.authc.UsernamePasswordToken {

    /**
     * 验证码
     */
    private String captcha;


    public UsernamePasswordToken() {
        super();
    }

    public UsernamePasswordToken(String username, String password, boolean rememberMe, String host, String captcha) {
        super(username, password, rememberMe, host);
        this.captcha = captcha;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
