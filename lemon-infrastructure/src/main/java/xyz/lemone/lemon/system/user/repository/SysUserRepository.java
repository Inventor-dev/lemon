package xyz.lemone.lemon.system.user.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import xyz.lemone.lemon.system.user.entity.SysUser;

/**
 * SysUserRepository.
 * @author lemon
 */
@Repository
public interface SysUserRepository extends R2dbcRepository<SysUser, String> {
    
    /**
     * 通过登录名查找.
     * @param username 登录名
     * @return user
     */
    Mono<SysUser> findByUsername(String username);
    
    /**
     * 通过手机号查找.
     * @param phone 手机号
     * @return user
     */
    Mono<SysUser> findByPhone(String phone);
}
