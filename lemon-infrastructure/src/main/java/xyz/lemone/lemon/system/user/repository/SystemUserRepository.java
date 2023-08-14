package xyz.lemone.lemon.system.user.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import xyz.lemone.lemon.system.user.entity.SystemUserDO;

import java.nio.channels.FileChannel;

/**
 * SysUserRepository.
 *
 * @author lemon
 */
@Repository
public interface SystemUserRepository extends R2dbcRepository<SystemUserDO, String> {
    
    /**
     * 通过登录名查找.
     *
     * @param username 登录名
     * @return user
     */
    Mono<SystemUserDO> findByUsername(String username);
    
    /**
     * 通过手机号查找.
     *
     * @param phone 手机号
     * @return user
     */
    Mono<SystemUserDO> findByPhone(String phone);
    
    /**
     * 通过用户ID 查找.
     * @param userid 用户ID
     * @return
     */
    Mono<SystemUserDO> findByUserid(String userid);
}
