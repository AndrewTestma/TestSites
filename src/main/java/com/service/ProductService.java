package com.service;

import com.pojo.Product;

import java.util.List;

/**
 * @Author:Andrew
 * @Description:
 * @Date 2018/1/11 0011
 */
public interface ProductService {
    int deleteByPrimaryKey(Integer tsproductid);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer tsproductid);

    List<Product> selectList(Integer tsuserid);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}
