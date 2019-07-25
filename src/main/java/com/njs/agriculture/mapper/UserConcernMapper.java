package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.UserConcern;

public interface UserConcernMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserConcern record);

    int insertSelective(UserConcern record);

    UserConcern selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserConcern record);

    int updateByPrimaryKey(UserConcern record);
}