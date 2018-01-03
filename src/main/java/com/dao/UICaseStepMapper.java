package com.dao;

import com.pojo.UICaseStep;
import org.springframework.stereotype.Repository;

/**
 * @Author:Andrew
 * @Description:测试用例及操作步骤视图
 * @Date 2018/1/3 0003
 */
@Repository("UICaseStepMapper")
public interface UICaseStepMapper {
    UICaseStep  selectByPrimaryKey(String tsnum);
}
