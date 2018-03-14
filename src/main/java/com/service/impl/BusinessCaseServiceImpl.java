package com.service.impl;

import com.dao.BusinessCaseMapper;
import com.pojo.BusinessCase;
import com.service.BusinessCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:Andrew
 * @Description:业务与测试用例的中间表
 * @Date 2018/2/26 0026
 */
@Service("BusinessCaseService")
public class BusinessCaseServiceImpl implements BusinessCaseService {
    @Autowired(required = false)
    private BusinessCaseMapper businessCaseMapper;
    @Override
    public int deleteByPrimaryKey(Integer tsbusinesscaseid) {
        return businessCaseMapper.deleteByPrimaryKey(tsbusinesscaseid);
    }

    @Override
    public int deleteByMore(Integer tsbusinessid, Integer tsuitestcaseid) {
        return businessCaseMapper.deleteByMore(tsbusinessid,tsuitestcaseid);
    }

    @Override
    public int insert(BusinessCase record) {
        return businessCaseMapper.insert(record);
    }

    @Override
    public int insertSelective(BusinessCase record) {
        return businessCaseMapper.insertSelective(record);
    }

    @Override
    public BusinessCase selectByPrimaryKey(Integer tsbusinesscaseid) {
        return businessCaseMapper.selectByPrimaryKey(tsbusinesscaseid);
    }

    @Override
    public List<Integer> selectBytsbusinessid(Integer tsbusinessid) {
        return businessCaseMapper.selectBytsbusinessid(tsbusinessid);
    }

    @Override
    public List<Integer> selectBytsuitestcaseid(Integer tsuitestcaseid) {
        return businessCaseMapper.selectBytsuitestcaseid(tsuitestcaseid);
    }

    @Override
    public Integer selectBytsorder(Integer tsbusinessid) {
        return businessCaseMapper.selectBytsorder(tsbusinessid);
    }

    @Override
    public int updateByPrimaryKeySelective(BusinessCase record) {
        return businessCaseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(BusinessCase record) {
        return businessCaseMapper.updateByPrimaryKey(record);
    }
}
