package com.dao;

import com.pojo.Result;

public interface ResultMapper {
    int deleteByPrimaryKey(Integer tsresultid);

    int insert(Result record);

    int insertSelective(Result record);

    Result selectByPrimaryKey(Integer tsresultid);

    int updateByPrimaryKeySelective(Result record);

    int updateByPrimaryKey(Result record);
}