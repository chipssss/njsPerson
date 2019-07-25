package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.ProcessRecord;

public interface ProcessRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProcessRecord record);

    int insertSelective(ProcessRecord record);

    ProcessRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProcessRecord record);

    int updateByPrimaryKey(ProcessRecord record);
}