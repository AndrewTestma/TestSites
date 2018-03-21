package com.service.impl;

import com.dao.UserMapper;
import com.pojo.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mr.Andrew
 * @Title: 用户接口实现
 * @Description: UserServiceImpl
 * @date 2018/3/2016:59
 */
@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Override
    public int deleteByPrimaryKey(Integer tsuserid) {
        return userMapper.deleteByPrimaryKey(tsuserid);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(Integer tsuserid) {
        return userMapper.selectByPrimaryKey(tsuserid);
    }

    @Override
    public User selectCount(String tsname, String tspassword) {
        return userMapper.selectCount(tsname,tspassword);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }
}
