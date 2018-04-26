package com.service.impl;

import com.dao.ProductMapper;
import com.pojo.Product;
import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:Andrew
 * @Description:
 * @Date 2018/1/11 0011
 */
@Service("ProductService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public int deleteByPrimaryKey(Integer tsproductid) {
        return productMapper.deleteByPrimaryKey(tsproductid);
    }

    @Override
    public int insert(Product record) {
        return productMapper.insert(record);
    }

    @Override
    public int insertSelective(Product record) {
        return productMapper.insertSelective(record);
    }

    @Override
    public Product selectByPrimaryKey(Integer tsproductid) {
        return productMapper.selectByPrimaryKey(tsproductid);
    }

    @Override
    public List<Product> selectList(int offset, int limit) {
        return productMapper.selectList(offset,limit);
    }


    @Override
    public int updateByPrimaryKeySelective(Product record) {
        return productMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Product record) {
        return productMapper.updateByPrimaryKey(record);
    }
}
