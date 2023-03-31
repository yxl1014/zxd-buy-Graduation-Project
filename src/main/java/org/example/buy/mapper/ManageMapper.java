package org.example.buy.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author yxl
 * @date 2023/3/27 下午5:44
 */

@Mapper
public interface ManageMapper {

    @Insert("insert into manage(admin_account,admin_password) values(#{a},#{p})")
    int insertManage(@Param("a") String account, @Param("p") String password);

    @Select("select count(1) from manage where admin_account = #{a}")
    int findManageByAccount(@Param("a") String account);

    @Select("select count(1) from manage where admin_account = #{a} and admin_password = #{p}")
    int findManageByAccountAndPassword(@Param("a") String account, @Param("p") String password);
}
