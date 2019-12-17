package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.RootRecordDO;

public interface RootRecordDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RootRecordDO record);

    int insertSelective(RootRecordDO record);

    RootRecordDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RootRecordDO record);

    int updateByPrimaryKey(RootRecordDO record);

    RootRecordDO selectNewByFieldId(Integer id);

    RootRecordDO selectNewByBatchId(String batchId);
}