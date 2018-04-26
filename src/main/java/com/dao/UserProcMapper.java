package com.dao;

import com.pojo.UserProc;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("UserProcMapper")
public interface UserProcMapper {
    int deleteByPrimaryKey(Integer tsuserprocid);

    int insert(UserProc record);

    int insertSelective(UserProc record);

    UserProc selectByPrimaryKey(Integer tsuserprocid);

    List<UserProc> selectListBytsuserid(@Param("tsuserid")Integer tsuserid);

    int updateByPrimaryKeySelective(UserProc record);

    int updateByPrimaryKey(UserProc record);
}