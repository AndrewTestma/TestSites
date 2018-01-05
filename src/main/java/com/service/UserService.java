package com.service;

import com.pojo.User;

/**
 * @Author:Andrew
 * @Description:
 * @Date 2018/1/5 0005
 */
public interface UserService {
    int deleteByPrimaryKey(Integer tsuserid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer tsuserid);

    User selectByLoginName(String tsloginname);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
