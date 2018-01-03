package com.impl;

import com.dao.UITestCaseMapper;
import com.pojo.UITestCase;
import com.service.UITestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:Andrew
 * @Description:
 * @Date 2017/12/28 0028
 */
@Service("UITestCaseService")
public class UITestCaseServiceImpl implements UITestCaseService {
    @Autowired
    private UITestCaseMapper uiTestCaseMapper;
    @Override
    public int deleteByPrimaryKey(Integer tsuitestcaseid) {
        return uiTestCaseMapper.deleteByPrimaryKey(tsuitestcaseid);
    }

    @Override
    public int insert(UITestCase record) {
        return uiTestCaseMapper.insertSelective(record);
    }

    @Override
    public int insertSelective(UITestCase record) {
        return uiTestCaseMapper.insertSelective(record);
    }

    @Override
    public UITestCase selectByPrimaryKey(Integer tsuitestcaseid) {
        return uiTestCaseMapper.selectByPrimaryKey(tsuitestcaseid);
    }

    @Override
    public List<UITestCase> selectByPrimaryKeyAll(int offset, int limit) {
        return uiTestCaseMapper.selectByPrimaryKeyAll(offset,limit);
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
