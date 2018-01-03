package com.dao;

import com.pojo.Verification;

public interface VerificationMapper {
    int deleteByPrimaryKey(Integer tsverificationid);

    int insert(Verification record);

    int insertSelective(Verification record);

    Verification selectByPrimaryKey(Integer tsverificationid);

    int updateByPrimaryKeySelective(Verification record);

    int updateByPrimaryKey(Verification record);
}