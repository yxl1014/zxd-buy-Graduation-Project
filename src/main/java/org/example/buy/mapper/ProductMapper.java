package org.example.buy.mapper;

import org.apache.ibatis.annotations.*;
import org.example.buy.entity.Product;

import java.util.List;

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

    @Select("select * from product where pid = #{pid}")
    Product selectProductByPid(@Param("pid") int pid);

    @Select("select * from product where bid = #{bid}")
    List<Product> selectProductByBid(@Param("bid") int bid);

    @Update("update product set amount = #{a}, counts = #{c} where pid = #{pid}")
    int updateProduct(@Param("a") float a,@Param("c") int c,@Param("pid") int pid);

    @Delete("delete from product where pid = #{pid}")
    int deleteProductByPid(@Param("pid") int pid);

    @Select("select count(1) from product")
    int selectCount();

    @Select("select * from product where pname = #{pname}")
    List<Product> selectProductByPName(@Param("pname") String pname);
}
