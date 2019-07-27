package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.productionBatch;

public interface productionBatchMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(productionBatch record);

    int insertSelective(productionBatch record);

    productionBatch selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(productionBatch record);

    int updateByPrimaryKey(productionBatch record);
}