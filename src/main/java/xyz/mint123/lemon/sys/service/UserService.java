package xyz.mint123.lemon.sys.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.mint123.lemon.core.base.BaseService;
import xyz.mint123.lemon.sys.entity.User;
import xyz.mint123.lemon.sys.repository.UserMapper;

import java.util.List;

/**
 * SYS 用户 service
 * @author lemon
 * @version 2018/2/26
 */
@Service
public class UserService extends BaseService<UserMapper,User,String> {

   public List<User> selectList(User entity){
        return baseMapper.selectList(entity);
   };

   public User selectOne(String id){
        return baseMapper.selectOne(id);
   };

}
