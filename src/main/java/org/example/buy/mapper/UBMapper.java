package org.example.buy.mapper;

import org.apache.ibatis.annotations.*;
import org.example.buy.entity.UB;

import java.util.List;

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

    @Delete("delete from u_b where car_id = #{car_id} and pid = #{pid}")
    int deleteUbByCarIdAndPid(@Param("car_id") int car_id, @Param("pid") int pid);

    @Select("select * from u_b where car_id = #{car_id}")
    List<UB> selectUbByCarId(@Param("car_id") int car_id);
}
