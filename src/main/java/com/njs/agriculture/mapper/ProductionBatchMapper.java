package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.ProductionBatch;

import java.util.List;

public interface ProductionBatchMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductionBatch record);

    int insertSelective(ProductionBatch record);

    ProductionBatch selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductionBatch record);

    int updateByPrimaryKey(ProductionBatch record);

    List<ProductionBatch> batchInfo(int fieldId);
}