package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.ServicePool;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ServicePoolMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ServicePool record);

    int insertSelective(ServicePool record);

    ServicePool selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ServicePool record);

    int updateByPrimaryKey(ServicePool record);

    List<Integer> selectServiceIdsByEnterprieIds(List<Integer> enterpriseIds);

    List<Integer> selectServiceIdsByUserId(int userId);

    List<ServicePool> selectByUserId(@Param("sourceId")int sourceId, @Param("source")int source);

    List<ServicePool> selectByStatus(int status);

    int validExist(@Param("serviceId") int serviceId, @Param("sourceId")int sourceId, @Param("source")int source);
}