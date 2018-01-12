package com.dao;

import com.pojo.UITestCase;

public interface UITestCaseMapper {
    int deleteByPrimaryKey(Integer tsuitestcaseid);

    int insert(UITestCase record);

    int insertSelective(UITestCase record);

    UITestCase selectByPrimaryKey(Integer tsuitestcaseid);

    int updateByPrimaryKeySelective(UITestCase record);

    int updateByPrimaryKey(UITestCase record);
}