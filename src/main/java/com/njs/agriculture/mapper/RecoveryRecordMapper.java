package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.RecoveryRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecoveryRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RecoveryRecord record);

    int insertSelective(RecoveryRecord record);

    RecoveryRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RecoveryRecord record);

    int updateByPrimaryKey(RecoveryRecord record);

    List<RecoveryRecord> selectBySource(@Param("source") int source, @Param("sourceId") int sourceId);
}