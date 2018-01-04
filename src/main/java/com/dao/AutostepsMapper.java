package com.dao;

import com.pojo.Autosteps;
import org.springframework.stereotype.Repository;

@Repository("AutostepsMapper")
public interface AutostepsMapper {
    int deleteByPrimaryKey(Integer tsautostepsid);

    int insert(Autosteps record);

    int insertSelective(Autosteps record);

    Autosteps selectByPrimaryKey(Integer tsautostepsid);

    Autosteps selectByName(String tsname);

    int updateByPrimaryKeySelective(Autosteps record);

    int updateByPrimaryKey(Autosteps record);
}