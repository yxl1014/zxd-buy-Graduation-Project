package org.example.buy.mapper;

import org.apache.ibatis.annotations.*;
import org.example.buy.entity.Business;

import java.util.List;

/**
 * @author yxl
 * @date 2023/3/31 下午1:18
 */

@Mapper
public interface BusinessMapper {

    @Insert("insert into business(b_password,b_name,product_count,all_amount,create_time,apply_id) " +
            "values(#{b_password},#{b_name},#{product_count},#{all_amount},#{create_time},#{apply_id})")
    int insertBusiness(Business business);

    @Select("select * from business")
    List<Business> selectAllBusiness();

    @Delete("delete from business where bid = #{bid}")
    int deleteBusinessByBid(@Param("bid") int bid);

    @Select("select * from business where bid = #{bid}")
    Business selectBusinessByBid(@Param("bid") int bid);

    @Select("select * from business where apply_id = #{aid}")
    Business selectBusinessByAid(@Param("aid") int aid);

}