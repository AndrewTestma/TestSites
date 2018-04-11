package com.dao;

import com.pojo.CaseSteps;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("CaseStepsMapper")
public interface CaseStepsMapper {

    int deleteByPrimaryKey(Integer tscasestepsid);

    int deleteByMore(CaseSteps caseSteps);

    int deleteBytsuitestcaseid(Integer tsuitestcaseid);

    int insert(CaseSteps record);

    int insertSelective(CaseSteps record);

    CaseSteps selectByPrimaryKey(Integer tscasestepsid);

    List<Integer> selectBytsuitestcaseid(Integer tsuitestcaseid);

    Integer selectBytsorder(Integer tsuitestcaseid);

    int updateByPrimaryKeySelective(CaseSteps record);

    int updateByPrimaryKey(CaseSteps record);
}