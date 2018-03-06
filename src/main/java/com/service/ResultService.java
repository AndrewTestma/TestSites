package com.service;
import com.pojo.Result;

/**
 * @program: TestSites
 * @description: 报告接口类
 * @author: Mr.Andrew
 * @create: 2018-03-06 09:53
 **/
public interface ResultService {
    int deleteByPrimaryKey(Integer tsresultid);

    int insert(Result record);

    int insertSelective(Result record);

    Result selectByPrimaryKey(Integer tsresultid);

    Result selectBybusinessid(Integer tsbusinessid);

    int updateByPrimaryKeySelective(Result record);

    int updateByPrimaryKey(Result record);
}
