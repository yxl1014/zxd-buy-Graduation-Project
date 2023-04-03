package org.example.buy.mapper;

import org.apache.ibatis.annotations.*;
import org.example.buy.entity.User;

import java.util.List;

/**
 * @author yxl
 * @date 2023/3/31 下午2:45
 */

@Mapper
public interface UserMapper {

    @Insert("insert into user(account,password,create_time) values(#{account},#{password},#{create_time})")
    int insertUser(User user);

    @Select("select * from user")
    List<User> selectAllUser();

    @Select("select * from user where account = #{account}")
    User selectUserByAccount(@Param("account") String account);

    @Select("select * from user where account = #{account} and password = #{password}")
    User selectUserByAccountAndPassword(@Param("account") String account, @Param("account") String password);

    @Delete("delete from user where account = #{account}")
    int deleteUserByAccount(@Param("account") String account);
}
