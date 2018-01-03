package com.dao;

import com.pojo.Module;

public interface ModuleMapper {
    int deleteByPrimaryKey(Integer tsmoduleid);

    int insert(Module record);

    int insertSelective(Module record);

    Module selectByPrimaryKey(Integer tsmoduleid);

    int updateByPrimaryKeySelective(Module record);

    int updateByPrimaryKey(Module record);
}