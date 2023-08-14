package xyz.lemone.lemon.system.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lemone.lemon.system.user.repository.SystemUserRepository;

/**
 * SysUserService.
 *
 * @author lime
 */
@Service
public class SystemUserService {
    
    @Autowired
    private SystemUserRepository sysUserRepository;
    
    
}
