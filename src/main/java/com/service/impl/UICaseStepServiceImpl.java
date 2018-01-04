package com.service.impl;

import com.dao.UICaseStepMapper;
import com.pojo.UICaseStep;
import com.service.UICaseStepService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author:Andrew
 * @Description:
 * @Date 2018/1/3 0003
 */
@Service("UICaseStepService")
public class UICaseStepServiceImpl implements UICaseStepService {
    @Resource(name = "UICaseStepMapper")
    private UICaseStepMapper uiCaseStepMapper;
    @Override
    public UICaseStep selectByPrimaryKey(Integer tsnum) {
        return uiCaseStepMapper.selectByPrimaryKey(tsnum);
    }
}
