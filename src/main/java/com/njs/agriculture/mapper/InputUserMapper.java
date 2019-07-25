package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.InputUser;

public interface InputUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InputUser record);

    int insertSelective(InputUser record);

    InputUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InputUser record);

    int updateByPrimaryKey(InputUser record);
}