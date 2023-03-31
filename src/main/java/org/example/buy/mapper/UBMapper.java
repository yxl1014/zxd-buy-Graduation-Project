package org.example.buy.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.buy.entity.UB;

/**
 * @author yxl
 * @date 2023/3/31 下午4:05
 */

@Mapper
public interface UBMapper {

    @Insert("insert into u_b(car_id,pid,counts,create_time) values(#{car_id},#{pid},#{counts},#{create_time})")
    int insertUB(UB ub);

    @Delete("delete from u_b where car_id = #{car_id}")
    int deleteUbByCarId(@Param("car_id") int car_id);
}
