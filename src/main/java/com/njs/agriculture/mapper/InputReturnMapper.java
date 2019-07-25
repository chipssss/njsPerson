package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.InputReturn;

public interface InputReturnMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InputReturn record);

    int insertSelective(InputReturn record);

    InputReturn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InputReturn record);

    int updateByPrimaryKey(InputReturn record);
}