package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.ProcessRecord;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ProcessRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProcessRecord record);

    int insertSelective(ProcessRecord record);

    ProcessRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProcessRecord record);

    int updateByPrimaryKey(ProcessRecord record);

    List<ProcessRecord> selectByCondition(@Param("startTime") Date startTime, @Param("endTime") Date endTime,
                                          @Param("fieldId") int fieldId, @Param("cropId") int cropId,
                                          @Param("sourceId") int sourceId, @Param("source") int source);

    List<ProcessRecord> selectByRecordIds(@Param("startTime") Date startTime, @Param("endTime") Date endTime,
                                          @Param("recordIds") List<Integer> recordIds);

    List<Integer> selectCropIdBySource(@Param("source") int source, @Param("sourceId") int sourceId);
}