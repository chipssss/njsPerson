package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.ServiceInfo;

import java.util.List;

public interface ServiceInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ServiceInfo record);

    int insertSelective(ServiceInfo record);

    ServiceInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ServiceInfo record);

    int updateByPrimaryKey(ServiceInfo record);

    List<ServiceInfo> selectAll();

    List<ServiceInfo> selectNotOwn(List<Integer> ownList);

    List<ServiceInfo> selectOwn(List<Integer> ownList);
}