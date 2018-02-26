package com.dao;

import com.pojo.BusinessCase;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @Description:业务与测试用例的中间表
 * */
@Repository("BusinessCaseMapper")
public interface BusinessCaseMapper {
    int deleteByPrimaryKey(Integer tsbusinesscaseid);

    int insert(BusinessCase record);

    int insertSelective(BusinessCase record);

    BusinessCase selectByPrimaryKey(Integer tsbusinesscaseid);

    List<Integer> selectBytsbusinessid(Integer tsbusinessid);

    int updateByPrimaryKeySelective(BusinessCase record);

    int updateByPrimaryKey(BusinessCase record);
}