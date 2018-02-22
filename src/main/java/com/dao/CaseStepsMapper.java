package com.dao;

import com.pojo.CaseSteps;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("CaseStepsMapper")
public interface CaseStepsMapper {
    int deleteByPrimaryKey(Integer tscasestepsid);

    int insert(CaseSteps record);

    int insertSelective(CaseSteps record);

    CaseSteps selectByPrimaryKey(Integer tscasestepsid);

    List<Integer> selectBytsuitestcaseid(Integer tsuitestcaseid);

    int updateByPrimaryKeySelective(CaseSteps record);

    int updateByPrimaryKey(CaseSteps record);
}