package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.Machining;

import java.util.List;

public interface MachiningMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Machining record);

    int insertSelective(Machining record);

    Machining selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Machining record);

    int updateByPrimaryKey(Machining record);

    List<Machining> selectByStockId(Integer id);

    List<Machining> selectBySource(int source, int sourceId);

    List<Machining> selectByKey(Integer machineId, Integer packId);
}