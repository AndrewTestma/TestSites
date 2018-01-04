package com.service;

import com.pojo.Autosteps;

/**
 * @Author:Andrew
 * @Description:
 * @Date 2017/12/29 0029
 */
public interface AutostepsService {
    int deleteByPrimaryKey(Integer tsautostepsid);

    int insert(Autosteps record);

    int insertSelective(Autosteps record);

    Autosteps selectByPrimaryKey(Integer tsautostepsid);

    Autosteps selectByName(String tsname);

    int updateByPrimaryKeySelective(Autosteps record);

    int updateByPrimaryKey(Autosteps record);
}
