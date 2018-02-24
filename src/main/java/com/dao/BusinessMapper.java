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

    List<Business> selectList(@Param("offset")int offset, @Param("limit")int limit);

    int updateByPrimaryKeySelective(Business record);

    int updateByPrimaryKey(Business record);
}