package xyz.lemone.lemon.system.user.manager;

import reactor.core.publisher.Mono;
import xyz.lemone.lemon.system.user.manager.model.SystemUser;

/**
 * 系统用户
 */
public interface SystemUserManager {
    
    /**
     * 通过用户名称查找用户.
     * @param username 用户名
     * @return
     */
    Mono<SystemUser> findByUsername(String username);
    
    /**
     * 通过手机号查找.
     * @param phone 手机号
     * @return
     */
    Mono<SystemUser> findByPhone(String phone);
    
    /**
     * 通过ID查找.
     * @param userid 用户id
     * @return
     */
    Mono<SystemUser> findByUserid(String userid);
}
