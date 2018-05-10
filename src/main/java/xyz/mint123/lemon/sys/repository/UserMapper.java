package xyz.mint123.lemon.sys.repository;

import org.apache.ibatis.annotations.*;
import java.util.List;

import xyz.mint123.lemon.core.base.BaseMapper;
import xyz.mint123.lemon.sys.entity.User;
/**
 * SYS 用户仓库
 * @author lemon
 * @version 2018/1/24
 */
@Mapper
public interface UserMapper extends BaseMapper<User,String> {

    /**
     * 查询 列表集合
     * @param entity
     * @return
     */
    @Select("select id, name from sys_user")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "name",column = "name")
    })
    List<User> selectList(User entity);

    /**
     * 通过ID查询单个
     * @param id
     * @return
     */
    @Select("select id, name from sys_user where id=#{id}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "name",column = "name")
    })
    User selectOne(@Param("id") String id);






}
