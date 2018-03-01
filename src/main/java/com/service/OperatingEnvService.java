package com.service;

import com.pojo.OperatingEnv;

import java.util.List;

/**
 * @Author:Andrew
 * @Description:
 * @Date 2018/2/28 0028
 */
public interface OperatingEnvService {
    int deleteByPrimaryKey(Integer tsoperatingenvid);

    int insert(OperatingEnv record);

    int insertSelective(OperatingEnv record);

    OperatingEnv selectByPrimaryKey(Integer tsoperatingenvid);

    List<OperatingEnv> selectList(int offset, int limit);

    int updateByPrimaryKeySelective(OperatingEnv record);

    int updateByPrimaryKey(OperatingEnv record);

    int updateByApply(Integer tsoperatingenvid);
}
