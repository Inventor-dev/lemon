package xyz.lemone.lemon.system.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lemone.lemon.system.user.entity.SysUser;
import xyz.lemone.lemon.system.user.mapper.SysUserMapper;
import xyz.lemone.lemon.system.user.wrapper.SysUserQuery;

import java.util.Optional;

/**
 * SysUserService.
 *
 * @author lime
 */
@Service
public class SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * username 查找用户.
     *
     * @param username 用户名
     * @return
     */
    public Optional<SysUser> loadByUsername(String username) {
        return Optional.ofNullable(sysUserMapper.findOne(new SysUserQuery()
                .where.username().eq(username).end()));
    }

    /**
     * 手机号查找用户.
     *
     * @param phone 手机号
     * @return
     */
    public Optional<SysUser> loadByPhone(String phone) {
        return Optional.ofNullable(sysUserMapper.findOne(new SysUserQuery()
                .where.phone().eq(phone).end()));
    }

}
