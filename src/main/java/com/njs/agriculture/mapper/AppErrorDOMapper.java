package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.AppErrorDO;

public interface AppErrorDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppErrorDO record);

    int insertSelective(AppErrorDO record);

    AppErrorDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppErrorDO record);

    int updateByPrimaryKeyWithBLOBs(AppErrorDO record);

    int updateByPrimaryKey(AppErrorDO record);
}