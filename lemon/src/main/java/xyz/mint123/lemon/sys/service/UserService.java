package xyz.mint123.lemon.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.mint123.lemon.sys.entity.User;
import xyz.mint123.lemon.sys.repository.UserMapper;

import java.util.List;

/**
 * SYS 用户 service
 * @author lemon
 * @version 2018/2/26
 */
@Service
public class UserService {

   @Autowired
   private UserMapper userMapper;

   public List<User> selectList(User entity){
        return userMapper.selectList(entity);
   };



}
