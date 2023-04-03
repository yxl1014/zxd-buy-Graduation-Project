package org.example.buy.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.buy.entity.Order_;

import java.util.List;

/**
 * @author yxl
 * @date 2023/4/2 下午12:01
 */
@Mapper
public interface OrderMapper {

    @Insert("insert into order_(bid,pid,pCount,user_account,all_amount,order_time) " +
            "values(#{bid},#{pid},#{pCount},#{user_account},#{all_amount},#{order_time})")
    int insertOrder(Order_ order_);

    @Select("select * from order_ where bid = #{bid}")
    List<Order_> selectAllByBid(@Param("bid") int bid);

    @Select("select * from order_ where user_account = #{user_account}")
    List<Order_> selectAllByAccount(@Param("user_account") String user_account);
}
