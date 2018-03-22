package com.dao;

import com.pojo.Module;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("ModuleMapper")
public interface ModuleMapper {
    int deleteByPrimaryKey(Integer tsmoduleid);

    int insert(Module record);

    int insertSelective(Module record);

    Module selectByPrimaryKey(Integer tsmoduleid);

    List<Module> selectList(@Param("tsproductid")Integer tsproductid);
    int updateByPrimaryKeySelective(Module record);

    int updateByPrimaryKey(Module record);
}