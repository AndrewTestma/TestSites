package com.dao;

import com.pojo.CaseSteps;

public interface CaseStepsMapper {
    int deleteByPrimaryKey(Integer tscasestepsid);

    int insert(CaseSteps record);

    int insertSelective(CaseSteps record);

    CaseSteps selectByPrimaryKey(Integer tscasestepsid);

    int updateByPrimaryKeySelective(CaseSteps record);

    int updateByPrimaryKey(CaseSteps record);
}