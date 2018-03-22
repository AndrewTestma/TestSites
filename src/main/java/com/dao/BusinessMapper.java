package com.dao;

import com.pojo.Business;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @Description:业务表
 * */
@Repository("BusinessMapper")
public interface BusinessMapper {
    int deleteByPrimaryKey(Integer tsbusinessid);

    int insert(Business record);

    int insertSelective(Business record);

    Business selectByPrimaryKey(Integer tsbusinessid);

    List<Business> selectList(@Param("tsproductid")Integer tsproductid);

    int updateByPrimaryKeySelective(Business record);

    int updateByPrimaryKey(Business record);
}