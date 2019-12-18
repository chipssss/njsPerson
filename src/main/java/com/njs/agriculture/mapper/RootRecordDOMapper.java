package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.RootRecordDO;

import java.util.List;

public interface RootRecordDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RootRecordDO record);

    int insertSelective(RootRecordDO record);

    RootRecordDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RootRecordDO record);

    int updateByPrimaryKey(RootRecordDO record);

    RootRecordDO selectNewByFieldId(int id);

    RootRecordDO selectNewByBatchId(String batchId);

    List<String> selectPackedBatchIdList();

    List<String> selectUnPackBatchIdListByFieldId(int fieldId);
}