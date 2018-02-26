package com.service.impl;

import com.dao.CaseStepsMapper;
import com.pojo.CaseSteps;
import com.service.CaseStepsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:Andrew
 * @Description:
 * @Date 2018/2/22 0022
 */
@Service("CaseStepsService")
public class CaseStepsServiceImpl implements CaseStepsService {
    @Resource(name="CaseStepsMapper")
    private CaseStepsMapper caseStepsMapper;
    @Override
    public int deleteByPrimaryKey(Integer tscasestepsid) {
        return caseStepsMapper.deleteByPrimaryKey(tscasestepsid);
    }

    @Override
    public int insert(CaseSteps record) {
        return caseStepsMapper.insert(record);
    }

    @Override
    public int insertSelective(CaseSteps record) {
        return caseStepsMapper.insertSelective(record);
    }

    @Override
    public CaseSteps selectByPrimaryKey(Integer tscasestepsid) {
        return caseStepsMapper.selectByPrimaryKey(tscasestepsid);
    }

    @Override
    public List<Integer> selectBytsuitestcaseid(Integer tsuitestcaseid) {
        return caseStepsMapper.selectBytsuitestcaseid(tsuitestcaseid);
    }

    @Override
    public Integer selectBytsorder(Integer tsuitestcaseid) {
        return caseStepsMapper.selectBytsorder(tsuitestcaseid);
    }

    @Override
    public int updateByPrimaryKeySelective(CaseSteps record) {
        return caseStepsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CaseSteps record) {
        return caseStepsMapper.updateByPrimaryKey(record);
    }
}
