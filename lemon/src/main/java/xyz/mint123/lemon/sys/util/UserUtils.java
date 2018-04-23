package xyz.mint123.lemon.sys.util;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import xyz.mint123.lemon.sys.entity.User;
import xyz.mint123.lemon.sys.service.UserService;

/**
 * 系统 用户工具类
 * @author Mint
 */
public class UserUtils {

    private static UserService userService;

    /**
     * 通过 登录名获取用户
     * @param username
     * @return
     */
    public static User getUserByLoginName(String username) {
        return userService.selectOne(new EntityWrapper<User>().eq("login_name",username));
    }

    @Deprecated
    public static void setUserService(UserService userService) {
        UserUtils.userService = userService;
    }
}
