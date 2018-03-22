package com.dao;

import com.pojo.UITestCase;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("UITestCaseMapper")
public interface UITestCaseMapper {
    int deleteByPrimaryKey(Integer tsuitestcaseid);

    int insert(UITestCase record);

    int insertSelective(UITestCase record);
    
    UITestCase selectByPrimaryKey(Integer tsuitestcaseid);

    List<UITestCase> selectList(@Param("tsproductid")Integer tsproductid);

    List<UITestCase> selectListByModuel(@Param("module") String module,@Param("tsproductid")Integer tsproductid);

    int updateByPrimaryKeySelective(UITestCase record);

    int updateByPrimaryKey(UITestCase record);
}