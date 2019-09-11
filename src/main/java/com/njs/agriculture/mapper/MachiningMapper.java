package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.Machining;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MachiningMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Machining record);

    int insertSelective(Machining record);

    Machining selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Machining record);

    int updateByPrimaryKey(Machining record);

    List<Machining> selectBySource(@Param("source")int source, @Param("sourceId")int sourceId);
}