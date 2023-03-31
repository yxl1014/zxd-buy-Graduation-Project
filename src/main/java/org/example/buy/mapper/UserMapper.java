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

    @Insert("insert into user(account,password,all_amount,create_time) values(#{account},#{password},#{all_amount},#{create_time})")
    int insertUser(User user);

    @Select("select * from user")
    List<User> selectAllUser();

    @Select("select * from user where account = #{account}")
    User selectUserByAccount(@Param("account") String account);

    @Delete("delete from user where account = #{account}")
    int deleteUserByAccount(@Param("account") String account);
}
