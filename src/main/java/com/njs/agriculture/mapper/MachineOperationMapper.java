package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.MachineOperation;

import java.util.List;

public interface MachineOperationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MachineOperation record);

    int insertSelective(MachineOperation record);

    MachineOperation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MachineOperation record);

    int updateByPrimaryKey(MachineOperation record);

    List<String> selectAll();
}