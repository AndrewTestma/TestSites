package com.service.impl;

import com.dao.AutostepsMapper;
import com.pojo.Autosteps;
import com.service.AutostepsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:Andrew
 * @Description:操作步骤服务实现类
 * @Date 2018/1/22 0022
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
    public List<Autosteps> selectList(int offset, int limit) {
        return autostepsMapper.selectList(offset,limit);
    }

    @Override
    public List<Autosteps> selectByModule(String moduleName) {
        return autostepsMapper.selectByModule(moduleName);
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
