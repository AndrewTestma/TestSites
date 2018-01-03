package com.service;

import com.pojo.UITestCase;

import java.util.List;

/**
 * @Author:Andrew
 * @Description:
 * @Date 2017/12/28 0028
 */
public interface UITestCaseService {
    int deleteByPrimaryKey(Integer tsuitestcaseid);

    int insert(UITestCase record);

    int insertSelective(UITestCase record);

    UITestCase selectByPrimaryKey(Integer tsuitestcaseid);

    List<UITestCase> selectByPrimaryKeyAll(int offset,int limit);

    int updateByPrimaryKeySelective(UITestCase record);

    int updateByPrimaryKey(UITestCase record);
}
