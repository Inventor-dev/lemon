package xyz.lemone.lemon.system.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import xyz.lemone.lemon.system.user.entity.SysUser;
import xyz.lemone.lemon.system.user.repository.SysUserRepository;

/**
 * SysUserService.
 *
 * @author lime
 */
@Service
public class SysUserService {
    
    @Autowired
    private SysUserRepository sysUserRepository;
    
    /**
     * username 查找用户.
     *
     * @param username 用户名
     * @return
     */
    public Mono<SysUser> loadByUsername(String username) {
        return sysUserRepository.findByUsername(username);
    }
    
    /**
     * 手机号查找用户.
     *
     * @param phone 手机号
     * @return
     */
    public Mono<SysUser> loadByPhone(String phone) {
        return sysUserRepository.findByPhone(phone);
    }
    
}
