package com.service.impl;

import com.dao.AutostepsMapper;
import com.pojo.Autosteps;
import com.service.AutostepsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author:Andrew
 * @Description:
 * @Date 2017/12/29 0029
 */
@Service("AutostepsService")
public class AutostepsServiceImpl implements AutostepsService {
    @Resource(name = "AutostepsMapper")
    private AutostepsMapper autostepsMapper;
    @Override
    public int deleteByPrimaryKey(Integer tsautostepsid) {
        return autostepsMapper.deleteByPrimaryKey(tsautostepsid);
    }

    @Override
    public int insert(Autosteps record) {
        return autostepsMapper.insert(record);
    }

    @Override
    public int insertSelective(Autosteps record) {
        return autostepsMapper.insertSelective(record);
    }

    @Override
    public Autosteps selectByPrimaryKey(Integer tsautostepsid) {
        return autostepsMapper.selectByPrimaryKey(tsautostepsid);
    }

    @Override
    public Autosteps selectByName(String tsname) {
        return autostepsMapper.selectByName(tsname);
    }

    @Override
    public int updateByPrimaryKeySelective(Autosteps record) {
        return autostepsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Autosteps record) {
        return autostepsMapper.updateByPrimaryKey(record);
    }
}
