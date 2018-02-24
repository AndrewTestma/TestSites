package com.service;

import com.pojo.Business;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author:Andrew
 * @Description:业务表接口
 * @Date 2018/2/24 0024
 */
public interface BusinessService {

    int deleteByPrimaryKey(Integer tsbusinessid);

    int insert(Business record);

    int insertSelective(Business record);

    Business selectByPrimaryKey(Integer tsbusinessid);

    List<Business> selectList(@Param("offset")int offset, @Param("limit")int limit);

    int updateByPrimaryKeySelective(Business record);

    int updateByPrimaryKey(Business record);
}
