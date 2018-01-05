package com.service.impl;

import com.dao.UserMapper;
import com.pojo.User;
import com.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author:Andrew
 * @Description:
 * @Date 2018/1/5 0005
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource(name="UserMapper")
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
    public User selectByLoginName(String tsloginname) {
        return userMapper.selectByLoginName(tsloginname);
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
