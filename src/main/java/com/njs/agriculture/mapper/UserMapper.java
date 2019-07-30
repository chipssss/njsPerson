package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int checkPhonenum(String phonenum);

    int checkUserName(String userName);

    User selectLogin(@Param("phonenum") String phonenum,@Param("password") String password);

    List<User> selectAll();
}