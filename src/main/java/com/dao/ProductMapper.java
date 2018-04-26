package com.dao;

import com.pojo.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("ProductMapper")
public interface ProductMapper {
    int deleteByPrimaryKey(Integer tsproductid);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer tsproductid);

    List<Product> selectList(@Param("tsuserid") Integer tsuserid);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}