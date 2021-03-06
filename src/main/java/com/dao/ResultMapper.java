package com.dao;

import com.pojo.Result;
import org.springframework.stereotype.Repository;

@Repository("ResultMapper")
public interface ResultMapper {
    int deleteByPrimaryKey(Integer tsresultid);

    int insert(Result record);

    int insertSelective(Result record);

    Result selectByPrimaryKey(Integer tsresultid);

    Result selectBybusinessid(Integer tsbusinessid);

    int updateByPrimaryKeySelective(Result record);

    int updateByPrimaryKey(Result record);
}