package com.service.impl;

import com.dao.OperatingEnvMapper;
import com.pojo.OperatingEnv;
import com.service.OperatingEnvService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:Andrew
 * @Description:运行环境实现类
 * @Date 2018/2/28 0028
 */
@Service("OperatingEnvService")
public class OperatingEnvServiceImpl implements OperatingEnvService {
    @Resource(name = "OperatingEnvMapper")
    private OperatingEnvMapper operatingEnvMapper;
    @Override
    public int deleteByPrimaryKey(Integer tsoperatingenvid) {
        return operatingEnvMapper.deleteByPrimaryKey(tsoperatingenvid);
    }

    @Override
    public int insert(OperatingEnv record) {
        return operatingEnvMapper.insert(record);
    }

    @Override
    public int insertSelective(OperatingEnv record) {
        return insertSelective(record);
    }

    @Override
    public OperatingEnv selectByPrimaryKey(Integer tsoperatingenvid) {
        return operatingEnvMapper.selectByPrimaryKey(tsoperatingenvid);
    }

    @Override
    public List<OperatingEnv> selectList(int offset, int limit) {
        return operatingEnvMapper.selectList(offset,limit);
    }

    @Override
    public int updateByPrimaryKeySelective(OperatingEnv record) {
        return operatingEnvMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(OperatingEnv record) {
        return operatingEnvMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByApply(Integer tsoperatingenvid) {
        return operatingEnvMapper.updateByApply(tsoperatingenvid);
    }
}
