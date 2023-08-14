package xyz.lemone.lemon.system.user.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import xyz.lemone.lemon.system.user.manager.model.SystemUser;
import xyz.lemone.lemon.system.user.repository.SystemUserRepository;
import xyz.lemone.lemon.system.user.support.SystemUserConvert;

@Component
public class SystemUserManagerImpl implements SystemUserManager {
    
    @Autowired
    private SystemUserRepository sysUserRepository;
    
    @Override
    public Mono<SystemUser> findByUsername(String username) {
        return sysUserRepository.findByUsername(username).map(SystemUserConvert.entityToModel);
    }
    
    @Override
    public Mono<SystemUser> findByPhone(String phone) {
        return sysUserRepository.findByPhone(phone).map(SystemUserConvert.entityToModel);
    }
    
    @Override
    public Mono<SystemUser> findByUserid(String userid) {
        return sysUserRepository.findByUserid(userid).map(SystemUserConvert.entityToModel);
    }
}
