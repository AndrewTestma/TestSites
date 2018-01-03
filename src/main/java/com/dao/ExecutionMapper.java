package com.dao;

import com.pojo.Execution;

public interface ExecutionMapper {
    int deleteByPrimaryKey(Integer tsexecutionid);

    int insert(Execution record);

    int insertSelective(Execution record);

    Execution selectByPrimaryKey(Integer tsexecutionid);

    int updateByPrimaryKeySelective(Execution record);

    int updateByPrimaryKey(Execution record);
}