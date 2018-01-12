package com.dao;

import com.pojo.Business;

public interface BusinessMapper {
    int deleteByPrimaryKey(Integer tsbusinessid);

    int insert(Business record);

    int insertSelective(Business record);

    Business selectByPrimaryKey(Integer tsbusinessid);

    int updateByPrimaryKeySelective(Business record);

    int updateByPrimaryKey(Business record);
}