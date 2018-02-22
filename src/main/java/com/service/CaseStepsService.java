package com.service;

import com.pojo.CaseSteps;

import java.util.List;

/**
 * @Author:Andrew
 * @Description:测试用例与操作步骤中间表
 * @Date 2018/2/22 0022
 */
public interface CaseStepsService {
    int deleteByPrimaryKey(Integer tscasestepsid);

    int insert(CaseSteps record);

    int insertSelective(CaseSteps record);

    CaseSteps selectByPrimaryKey(Integer tscasestepsid);

    List<Integer> selectBytsuitestcaseid(Integer tsuitestcaseid);

    int updateByPrimaryKeySelective(CaseSteps record);

    int updateByPrimaryKey(CaseSteps record);
}
