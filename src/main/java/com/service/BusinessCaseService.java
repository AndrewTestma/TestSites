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

    int deleteByMore(Integer tsbusinessid,Integer tsuitestcaseid);

    int deleteBytsbusinessid(Integer tsbusinessid);

    int insert(BusinessCase record);

    int insertSelective(BusinessCase record);

    BusinessCase selectByPrimaryKey(Integer tsbusinesscaseid);

    List<Integer> selectBytsbusinessid(Integer tsbusinessid);

    List<Integer> selectBytsuitestcaseid(Integer tsuitestcaseid);

    Integer selectBytsorder(Integer tsbusinessid);

    int updateByPrimaryKeySelective(BusinessCase record);

    int updateByPrimaryKey(BusinessCase record);
}
