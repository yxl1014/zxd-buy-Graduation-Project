package org.example.buy.mapper;

import org.apache.ibatis.annotations.*;
import org.example.buy.entity.Product;

/**
 * @author yxl
 * @date 2023/3/31 下午1:25
 */

@Mapper
public interface ProductMapper {

    @Insert("insert into product(pname,amount,counts,bid,img,msg) values(#{pname},#{amount},#{counts},#{bid},#{img},#{msg})")
    int insertProduct(Product product);

    @Select("select count(1) from product where bid = #{bid}")
    int selectCountByBid(@Param("bid") int bid);

    @Delete("delete from product where bid = #{bid}")
    int deleteProductByBid(@Param("bid") int bid);
}
