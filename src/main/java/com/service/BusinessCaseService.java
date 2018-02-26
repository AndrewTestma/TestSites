package com.service;

import com.pojo.BusinessCase;

import java.util.List;

/**
 * @Author:Andrew
 * @Description:业务与测试用例的中间表
 * @Date 2018/2/26 0026
 */
public interface BusinessCaseService {
    int deleteByPrimaryKey(Integer tsbusinesscaseid);

    int insert(BusinessCase record);

    int insertSelective(BusinessCase record);

    BusinessCase selectByPrimaryKey(Integer tsbusinesscaseid);

    List<Integer> selectBytsbusinessid(Integer tsbusinessid);

    int updateByPrimaryKeySelective(BusinessCase record);

    int updateByPrimaryKey(BusinessCase record);
}
