package com.dao;

import com.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("UserMapper")
public interface UserMapper {
    int deleteByPrimaryKey(Integer tsuserid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer tsuserid);

    User selectByLoginName(@Param("tsloginname") String tsloginname,@Param("tspassword")String tspassword);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}