package org.example.buy.mapper;

import org.apache.ibatis.annotations.*;
import org.example.buy.entity.Buy_Car;

/**
 * @author yxl
 * @date 2023/3/31 下午4:00
 */

@Mapper
public interface BuyCarMapper {

    @Insert("insert into buy_car(user_account) values(#{account})")
    int insertBuyCar(@Param("account") String account);

    @Delete("delete from buy_car where car_id = #{id}")
    int deleteCarByCarId(@Param("id") Integer id);

    @Select("select * from buy_car where user_account = #{account}")
    Buy_Car selectBuyCarByAccount(@Param("account") String account);

    @Update("update buy_car set counts = counts + 1 where car_id = #{id}")
    int updateCountsById(@Param("id") int car_id);
}
