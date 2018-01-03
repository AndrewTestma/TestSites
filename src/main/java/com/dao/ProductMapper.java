package com.dao;

import com.pojo.Product;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer tsproductid);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer tsproductid);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}