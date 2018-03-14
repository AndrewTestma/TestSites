package com.service;

import com.pojo.UITestCase;

import java.util.List;

/**
 * @Author:Andrew
 * @Description:UI测试用例服务接口
 * @Date 2018/1/15 0015
 */
public interface UITestCaseService {

    int deleteByPrimaryKey(Integer tsuitestcaseid);

    int insert(UITestCase record);

    int insertSelective(UITestCase record);

    UITestCase selectByPrimaryKey(Integer tsuitestcaseid);

    List<UITestCase> selectList(int offset, int limit);

    List<UITestCase> selectListByModuel( String module);

    int updateByPrimaryKeySelective(UITestCase record);

    int updateByPrimaryKey(UITestCase record);
}
