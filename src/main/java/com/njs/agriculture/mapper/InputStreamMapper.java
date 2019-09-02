package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.InputStream;

public interface InputStreamMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InputStream record);

    int insertSelective(InputStream record);

    InputStream selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InputStream record);

    int updateByPrimaryKey(InputStream record);
}