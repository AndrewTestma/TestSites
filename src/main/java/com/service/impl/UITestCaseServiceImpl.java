package com.service.impl;

import com.dao.UITestCaseMapper;
import com.pojo.UITestCase;
import com.service.UITestCaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:Andrew
 * @Description:
 * @Date 2018/1/15 0015
 */
@Service("UITestCaseService")
public class UITestCaseServiceImpl implements UITestCaseService {
    @Resource(name = "UITestCaseMapper")
    private UITestCaseMapper uiTestCaseMapper;
    @Override
    public int deleteByPrimaryKey(Integer tsuitestcaseid) {
        return uiTestCaseMapper.deleteByPrimaryKey(tsuitestcaseid);
    }

    @Override
    public int insert(UITestCase record) {
        return uiTestCaseMapper.insert(record);
    }

    @Override
    public int insertSelective(UITestCase record) {
        return uiTestCaseMapper.insertSelective(record);
    }

    @Override
    public int selectByname(String tsname) {
        return uiTestCaseMapper.selectByname(tsname);
    }

    @Override
    public UITestCase selectByPrimaryKey(Integer tsuitestcaseid) {
        return uiTestCaseMapper.selectByPrimaryKey(tsuitestcaseid);
    }

    @Override
    public List<UITestCase> selectList(int offset, int limit) {
        return uiTestCaseMapper.selectList(offset,limit);
    }

    @Override
    public int updateByPrimaryKeySelective(UITestCase record) {
        return uiTestCaseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UITestCase record) {
        return uiTestCaseMapper.updateByPrimaryKey(record);
    }
}
