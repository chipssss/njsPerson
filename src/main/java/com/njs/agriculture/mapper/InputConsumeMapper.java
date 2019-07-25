package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.InputConsume;

public interface InputConsumeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InputConsume record);

    int insertSelective(InputConsume record);

    InputConsume selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InputConsume record);

    int updateByPrimaryKey(InputConsume record);
}