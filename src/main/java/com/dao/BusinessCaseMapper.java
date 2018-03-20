package com.dao;

import com.pojo.BusinessCase;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @Description:业务与测试用例的中间表
 * */
@Repository("BusinessCaseMapper")
public interface BusinessCaseMapper {
    int deleteByPrimaryKey(Integer tsbusinesscaseid);

    int deleteByMore(@Param("tsbusinessid")Integer tsbusinessid,@Param("tsuitestcaseid")Integer tsuitestcaseid);

    int deleteBytsbusinessid(@Param("tsbusinessid") Integer tsbusinessid);

    int insert(BusinessCase record);

    int insertSelective(BusinessCase record);

    BusinessCase selectByPrimaryKey(Integer tsbusinesscaseid);

    List<Integer> selectBytsbusinessid(Integer tsbusinessid);

    List<Integer> selectBytsuitestcaseid(Integer tsuitestcaseid);

    //返回order
    Integer selectBytsorder(Integer tsbusinessid);

    int updateByPrimaryKeySelective(BusinessCase record);

    int updateByPrimaryKey(BusinessCase record);
}