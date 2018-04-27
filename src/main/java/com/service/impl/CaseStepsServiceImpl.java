package com.service.impl;

import com.dao.CaseStepsMapper;
import com.pojo.CaseSteps;
import com.service.CaseStepsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author:Andrew
 * @Description:
 * @Date 2018/2/22 0022
 */
@Service("CaseStepsService")
public class CaseStepsServiceImpl implements CaseStepsService {
    @Autowired(required = false)
    private CaseStepsMapper caseStepsMapper;
    @Override
    public int deleteByPrimaryKey(Integer tscasestepsid) {
        return caseStepsMapper.deleteByPrimaryKey(tscasestepsid);
    }
    /**
     * @Description:根据测试用例ID删除中间表关系
     * @param:[tsuitestcaseid]:测试用例ID
     * @return:执行结果
     * @date:2018/4/11 13:44
     */
    @Override
    public int deleteBytsuitestcaseid(Integer tsuitestcaseid) {
        return caseStepsMapper.deleteBytsuitestcaseid(tsuitestcaseid);
    }

    @Override
    public int deleteByMore(CaseSteps caseSteps) {
        return caseStepsMapper.deleteByMore(caseSteps);
    }

    @Override
    public int insert(CaseSteps record) {
        return caseStepsMapper.insert(record);
    }

    @Override
    public int insertBatch(List<CaseSteps> sorts) {
        return caseStepsMapper.insertBatch(sorts);
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
