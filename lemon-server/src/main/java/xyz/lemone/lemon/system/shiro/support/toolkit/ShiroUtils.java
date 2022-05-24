package xyz.lemone.lemon.system.shiro.support.toolkit;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

import java.util.Optional;

/**
 * ShiroUtils.
 *
 * @author lemon
 */
public class ShiroUtils {

    /**
     * 加密算法.
     */
    public static final String HASH_ALGORITHM_NAME = Sha256Hash.ALGORITHM_NAME;

    /**
     * 循环次数.
     */
    public static final int HASH_ITERATIONS = 30;

    /**
     * 盐 密码分隔符.
     */
    public static final String PASSWORD_SPIDER = "@";

    /**
     * 生成盐.
     *
     * @return
     */
    public static String genSalt() {
        return RandomStringUtils.randomAlphanumeric(20);
    }

    /**
     * 生成摘要密码.
     *
     * @param password 密码
     * @param salt 盐
     * @return hash 值
     */
    public static String hash(String password, String salt) {
        return new SimpleHash(HASH_ALGORITHM_NAME, password, salt, HASH_ITERATIONS).toString();
    }

    /**
     * 生成密码.
     *
     * @param password 明文密码
     * @return 密文密码
     */
    public static String genPassword(String password) {
        String salt = genSalt();
        String hash = hash(password, salt);
        return StringUtils.joinWith(PASSWORD_SPIDER, salt, hash);
    }

    /**
     * 获取盐和密码.
     *
     * @param fullPassword 完整加密密码
     * @return
     */
    public static String[] getSaltPassword(String fullPassword) {
        fullPassword = Optional.ofNullable(fullPassword).orElse(" @ ");
        String[] split = fullPassword.split(PASSWORD_SPIDER);
        if (split.length < 2) {
            split = new String[]{"", ""};
        }
        return split;
    }

    /**
     * 验证密码是否正确.
     * @param fullPassword 密文密码
     * @param plaintPassword 明文密码
     * @return
     */
    public static Boolean comparison(String fullPassword, String plaintPassword) {
        String[] saltPassword = getSaltPassword(fullPassword);
        return saltPassword[NumberUtils.INTEGER_ONE].equals(
                hash(plaintPassword, saltPassword[NumberUtils.INTEGER_ZERO]));
    }

    public static void main(String[] args) {
        System.out.println(genPassword("123456"));
    }

}
