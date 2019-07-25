package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.UserRelationship;

public interface UserRelationshipMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRelationship record);

    int insertSelective(UserRelationship record);

    UserRelationship selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRelationship record);

    int updateByPrimaryKey(UserRelationship record);
}