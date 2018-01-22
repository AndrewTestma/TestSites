package com.service;

import com.pojo.Autosteps;

import java.util.List;

/**
 * @Author:Andrew
 * @Description:操作步骤服务service
 * @Date 2018/1/22 0022
 */
public interface AutostepsService {
    int deleteByPrimaryKey(Integer tsautostepsid);

    int insert(Autosteps record);

    int insertSelective(Autosteps record);

    Autosteps selectByPrimaryKey(Integer tsautostepsid);

    List<Autosteps> selectList(int offset, int limit);

    int updateByPrimaryKeySelective(Autosteps record);

    int updateByPrimaryKey(Autosteps record);
}
