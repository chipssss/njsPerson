package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.InputUsed;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InputUsedMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InputUsed record);

    int insertSelective(InputUsed record);

    InputUsed selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InputUsed record);

    int updateByPrimaryKey(InputUsed record);

    List<InputUsed> selectBySource(@Param("source") int source, @Param("sourceId") int sourceId);
}