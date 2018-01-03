package com.dao;

import com.pojo.Search;

public interface SearchMapper {
    int deleteByPrimaryKey(Integer tssearchid);

    int insert(Search record);

    int insertSelective(Search record);

    Search selectByPrimaryKey(Integer tssearchid);

    int updateByPrimaryKeySelective(Search record);

    int updateByPrimaryKey(Search record);
}