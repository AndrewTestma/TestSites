package com.dao;

import com.pojo.Permissions;
import org.springframework.stereotype.Repository;

@Repository("PermissionsMapper")
public interface PermissionsMapper {
    int deleteByPrimaryKey(Integer tspermissionsid);

    int insert(Permissions record);

    int insertSelective(Permissions record);

    Permissions selectByPrimaryKey(Integer tspermissionsid);

    int updateByPrimaryKeySelective(Permissions record);

    int updateByPrimaryKey(Permissions record);
}