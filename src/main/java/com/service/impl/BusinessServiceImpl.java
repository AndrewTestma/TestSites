package com.service.impl;

import com.dao.BusinessMapper;
import com.pojo.Business;
import com.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:Andrew
 * @Description:实现业务表
 * @Date 2018/2/24 0024
 */
@Service("BusinessService")
public class BusinessServiceImpl implements BusinessService {
    @Autowired
    private BusinessMapper businessMapper;
    @Override
    public int deleteByPrimaryKey(Integer tsbusinessid) {
      return businessMapper.deleteByPrimaryKey(tsbusinessid);
    }

    @Override
    public int insert(Business record) {
       return businessMapper.insert(record);
    }

    @Override
    public int insertSelective(Business record) {
        return businessMapper.insertSelective(record);
    }

    @Override
    public Business selectByPrimaryKey(Integer tsbusinessid) {
        return businessMapper.selectByPrimaryKey(tsbusinessid);
    }

    @Override
    public List<Business> selectList(Integer tsproductid) {
        return businessMapper.selectList(tsproductid);
    }

    @Override
    public int updateByPrimaryKeySelective(Business record) {
        return businessMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Business record) {
        return businessMapper.updateByPrimaryKey(record);
    }
}
