package com.service;

import com.pojo.UserProc;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Mr.Andrew
 * @Description: 用户与产品中间表
 * @date 2018/4/25 18:07
 */
public interface UserProcService {

    int deleteByPrimaryKey(Integer tsuserprocid);

    int insert(UserProc record);

    int insertSelective(UserProc record);

    UserProc selectByPrimaryKey(Integer tsuserprocid);

    List<UserProc> selectListBytsuserid(Integer tsuserid);

    int updateByPrimaryKeySelective(UserProc record);

    int updateByPrimaryKey(UserProc record);
}
