package com.service.impl;

import com.dao.ModuleMapper;
import com.pojo.Module;
import com.service.ModuleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:Andrew
 * @Description:模块服务接口实现
 * @Date 2018/1/15 0015
 */
@Service("ModuleService")
public class ModuleServiceImpl implements ModuleService {
    @Resource(name = "ModuleMapper")
    private ModuleMapper moduleMapper;
    @Override
    public int deleteByPrimaryKey(Integer tsmoduleid) {
        return moduleMapper.deleteByPrimaryKey(tsmoduleid);
    }

    @Override
    public int insert(Module record) {
        return moduleMapper.insert(record);
    }

    @Override
    public int insertSelective(Module record) {
        return moduleMapper.insertSelective(record);
    }

    @Override
    public Module selectByPrimaryKey(Integer tsmoduleid) {
        return moduleMapper.selectByPrimaryKey(tsmoduleid);
    }

    @Override
    public List<Module> selectList(int offset, int limit) {
        return moduleMapper.selectList(offset,limit);
    }

    @Override
    public int updateByPrimaryKeySelective(Module record) {
        return moduleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Module record) {
        return moduleMapper.updateByPrimaryKey(record);
    }
}
