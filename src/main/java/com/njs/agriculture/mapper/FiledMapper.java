package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.Filed;

public interface FiledMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Filed record);

    int insertSelective(Filed record);

    Filed selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Filed record);

    int updateByPrimaryKey(Filed record);
}