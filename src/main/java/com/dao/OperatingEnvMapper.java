package com.dao;

import com.pojo.OperatingEnv;

public interface OperatingEnvMapper {
    int deleteByPrimaryKey(Integer tsoperatingenvid);

    int insert(OperatingEnv record);

    int insertSelective(OperatingEnv record);

    OperatingEnv selectByPrimaryKey(Integer tsoperatingenvid);

    int updateByPrimaryKeySelective(OperatingEnv record);

    int updateByPrimaryKey(OperatingEnv record);
}