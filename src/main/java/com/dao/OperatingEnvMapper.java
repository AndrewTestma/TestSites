package com.dao;

import com.pojo.OperatingEnv;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("OperatingEnvMapper")
public interface OperatingEnvMapper {
    int deleteByPrimaryKey(Integer tsoperatingenvid);

    int insert(OperatingEnv record);

    int insertSelective(OperatingEnv record);

    OperatingEnv selectByPrimaryKey(Integer tsoperatingenvid);

    List<OperatingEnv> selectList(@Param("offset")int offset, @Param("limit")int limit);

    int updateByPrimaryKeySelective(OperatingEnv record);

    int updateByPrimaryKey(OperatingEnv record);

    int updateByApply(Integer tsoperatingenvid);
}