package com.service.impl;
import com.dao.ResultMapper;
import com.pojo.Result;
import com.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: TestSites
 * @description: 报告接口实现类
 * @author: Mr.Andrew
 * @create: 2018-03-06 09:54
 **/
@Service
public class ResultServiceImpl implements ResultService {
    @Autowired(required = false)
    private ResultMapper resultMapper;
    @Override
    public int deleteByPrimaryKey(Integer tsresultid) {
        return resultMapper.deleteByPrimaryKey(tsresultid);
    }

    @Override
    public int insert(Result record) {
        return resultMapper.insert(record);
    }

    @Override
    public int insertSelective(Result record) {
        return resultMapper.insertSelective(record);
    }

    @Override
    public Result selectByPrimaryKey(Integer tsresultid) {
        return resultMapper.selectByPrimaryKey(tsresultid);
    }

    @Override
    public Result selectBybusinessid(Integer tsbusinessid) {
        return resultMapper.selectBybusinessid(tsbusinessid);
    }

    @Override
    public int updateByPrimaryKeySelective(Result record) {
        return resultMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Result record) {
        return resultMapper.updateByPrimaryKey(record);
    }
}
