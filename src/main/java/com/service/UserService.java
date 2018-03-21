package com.service;

import com.pojo.User;

/**
 * @author Mr.Andrew
 * @Title: UserService
 * @Description: 用户接口
 * @date 2018/3/2016:56
 */
public interface UserService {

    int deleteByPrimaryKey(Integer tsuserid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer tsuserid);

    User selectCount(String tsname,String tspassword);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
