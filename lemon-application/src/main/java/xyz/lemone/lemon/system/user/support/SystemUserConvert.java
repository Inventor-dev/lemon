package xyz.lemone.lemon.system.user.support;

import xyz.lemone.lemon.system.user.entity.SystemUserDO;
import xyz.lemone.lemon.system.user.manager.model.SystemUser;

import java.util.function.Function;

public class SystemUserConvert {
    
    public static Function<SystemUserDO, SystemUser> entityToModel = entity -> {
        SystemUser model = new SystemUser();
        model.setUserid(entity.getUserid());
        model.setUsername(entity.getUsername());
        model.setPassword(entity.getPassword());
        model.setRealName(entity.getRealName());
        model.setAvatar(entity.getAvatar());
        model.setPhone(entity.getPhone());
        model.setEmail(entity.getEmail());
        return model;
    };
}
