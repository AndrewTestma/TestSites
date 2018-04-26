package com.service.impl;

import com.dao.UserProcMapper;
import com.pojo.UserProc;
import com.service.UserProcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mr.Andrew
 * @Description: 用户与产品中间表实现类
 * @date 2018/4/25 18:09
 */
@Service("UserProcService")
public class UserProcServiceImpl implements UserProcService{
    @Autowired
    private UserProcMapper userProcMapper;

    @Override
    public int deleteByPrimaryKey(Integer tsuserprocid) {
        return userProcMapper.deleteByPrimaryKey(tsuserprocid);
    }

    @Override
    public int insert(UserProc record) {
        return userProcMapper.insert(record);
    }

    @Override
    public int insertSelective(UserProc record) {
        return userProcMapper.insertSelective(record);
    }

    @Override
    public UserProc selectByPrimaryKey(Integer tsuserprocid) {
        return userProcMapper.selectByPrimaryKey(tsuserprocid);
    }

    @Override
    public List<UserProc> selectListBytsuserid(Integer tsuserid) {
        return userProcMapper.selectListBytsuserid(tsuserid);
    }

    @Override
    public int updateByPrimaryKeySelective(UserProc record) {
        return userProcMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserProc record) {
        return userProcMapper.updateByPrimaryKey(record);
    }
}
