package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.ProcessImage;

public interface ProcessImageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProcessImage record);

    int insertSelective(ProcessImage record);

    ProcessImage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProcessImage record);

    int updateByPrimaryKey(ProcessImage record);
}