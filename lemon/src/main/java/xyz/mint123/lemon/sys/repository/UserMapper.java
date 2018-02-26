package xyz.mint123.lemon.sys.repository;

import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * SYS 用户仓库
 * @author lemon
 * @version 2018/1/24
 */
@Mapper
public interface UserMapper<User> {

    /**
     * 新增
     * @param entity
     * @return
     */
    @Insert("insert into sys_user(id,name,login_name,code) values (#{id},#{name},#{loginName},#{code})  ")
    int insert (User entity);

    /**
     * 修改
     * @param entity
     * @return
     */
    int update (User entity);

    /**
     * 删除
     * @param entity
     * @return
     */
    int delete (User entity);

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









}
