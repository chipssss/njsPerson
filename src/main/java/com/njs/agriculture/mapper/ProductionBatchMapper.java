package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.ProductionBatch;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ProductionBatchMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductionBatch record);

    int insertSelective(ProductionBatch record);

    ProductionBatch selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductionBatch record);

    int updateByPrimaryKey(ProductionBatch record);

    List<ProductionBatch> batchInfo(int fieldId);

    ProductionBatch onlyBatch(@Param("fieldId") int fieldId, @Param("today") Date today);
}