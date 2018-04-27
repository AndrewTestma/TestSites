package com.service;

import com.pojo.CaseSteps;

import java.util.List;
import java.util.Map;

/**
 * @Author:Andrew
 * @Description:测试用例与操作步骤中间表
 * @Date 2018/2/22 0022
 */
public interface CaseStepsService {
    int deleteByPrimaryKey(Integer tscasestepsid);

    int deleteBytsuitestcaseid(Integer tsuitestcaseid);

    int deleteByMore(CaseSteps caseSteps);

    int insert(CaseSteps record);

    int insertBatch(List<CaseSteps> sorts);

    int insertSelective(CaseSteps record);

    CaseSteps selectByPrimaryKey(Integer tscasestepsid);

    List<Integer> selectBytsuitestcaseid(Integer tsuitestcaseid);

    Integer selectBytsorder(Integer tsuitestcaseid);

    int updateByPrimaryKeySelective(CaseSteps record);

    int updateByPrimaryKey(CaseSteps record);
}
