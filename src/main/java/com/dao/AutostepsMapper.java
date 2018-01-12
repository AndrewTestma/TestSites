package com.dao;

import com.pojo.Autosteps;

public interface AutostepsMapper {
    int deleteByPrimaryKey(Integer tsautostepsid);

    int insert(Autosteps record);

    int insertSelective(Autosteps record);

    Autosteps selectByPrimaryKey(Integer tsautostepsid);

    int updateByPrimaryKeySelective(Autosteps record);

    int updateByPrimaryKey(Autosteps record);
}