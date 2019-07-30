package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.InputUsed;

public interface InputUsedMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InputUsed record);

    int insertSelective(InputUsed record);

    InputUsed selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InputUsed record);

    int updateByPrimaryKey(InputUsed record);
}