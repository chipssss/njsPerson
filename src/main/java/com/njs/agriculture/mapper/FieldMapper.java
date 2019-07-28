package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.Field;

import java.util.List;

public interface FieldMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Field record);

    int insertSelective(Field record);

    Field selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Field record);

    int updateByPrimaryKey(Field record);

    List<Field> selectBySourceId(int sourceId);

}