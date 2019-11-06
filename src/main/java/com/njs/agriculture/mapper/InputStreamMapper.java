package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.InputStream;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InputStreamMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InputStream record);

    int insertSelective(InputStream record);

    InputStream selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InputStream record);

    int updateByPrimaryKey(InputStream record);

    List<InputStream> selectBySource(@Param("source") int source, @Param("sourceId") int sourceId);

    List<InputStream> selectByRecordId(int recordId);
}