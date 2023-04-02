package org.example.buy.mapper;

import org.apache.ibatis.annotations.*;
import org.example.buy.entity.Apply;

import java.util.List;

/**
 * @author yxl
 * @date 2023/3/31 下午1:46
 */

@Mapper
public interface ApplyMapper {

    @Insert("insert into apply(apply_name,apply_card_id,apply_tel,apply_bname,register_amount) " +
            "values(#{apply_name},#{apply_card_id},#{apply_tel},#{apply_bname},#{register_amount})")
    int insertApply(Apply apply);

    @Select("select * from apply where status = #{status}")
    List<Apply> selectAllApplyByStatus(@Param("status") boolean status);

    @Delete("delete from apply where aid = #{aid}")
    int deleteApplyByAid(@Param("aid") int aid);

    @Select("select * from apply where aid = #{aid}")
    Apply selectApplyByAid(@Param("aid") int aid);
}
