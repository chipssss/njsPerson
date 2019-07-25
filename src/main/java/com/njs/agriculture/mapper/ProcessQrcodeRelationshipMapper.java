package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.ProcessQrcodeRelationship;

public interface ProcessQrcodeRelationshipMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProcessQrcodeRelationship record);

    int insertSelective(ProcessQrcodeRelationship record);

    ProcessQrcodeRelationship selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProcessQrcodeRelationship record);

    int updateByPrimaryKey(ProcessQrcodeRelationship record);
}