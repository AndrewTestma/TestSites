package com.dao;

import com.pojo.BusinessCase;

public interface BusinessCaseMapper {
    int deleteByPrimaryKey(Integer tsbusinesscaseid);

    int insert(BusinessCase record);

    int insertSelective(BusinessCase record);

    BusinessCase selectByPrimaryKey(Integer tsbusinesscaseid);

    int updateByPrimaryKeySelective(BusinessCase record);

    int updateByPrimaryKey(BusinessCase record);
}