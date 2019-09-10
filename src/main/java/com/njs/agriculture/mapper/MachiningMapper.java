package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.Machining;

public interface MachiningMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Machining record);

    int insertSelective(Machining record);

    Machining selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Machining record);

    int updateByPrimaryKey(Machining record);
}