package com.service;

import com.pojo.Module;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author:Andrew
 * @Description:模块服务接口
 * @Date 2018/1/15 0015
 */
public interface ModuleService {
    int deleteByPrimaryKey(Integer tsmoduleid);

    int insert(Module record);

    int insertSelective(Module record);

    Module selectByPrimaryKey(Integer tsmoduleid);

    List<Module> selectList(Integer tsproductid);
    int updateByPrimaryKeySelective(Module record);

    int updateByPrimaryKey(Module record);
}
