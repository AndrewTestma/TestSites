package com.dao;

import com.pojo.Autosteps;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("AutostepsMapper")
public interface AutostepsMapper {
    int deleteByPrimaryKey(Integer tsautostepsid);

    int insert(Autosteps record);

    int insertSelective(Autosteps record);

    Autosteps selectByPrimaryKey(Integer tsautostepsid);

    List<Autosteps> selectList(@Param("offset") int offset,@Param("limit") int limit);

    int updateByPrimaryKeySelective(Autosteps record);

    int updateByPrimaryKey(Autosteps record);
}