package com.njs.agriculture.mapper;

import com.njs.agriculture.VO.FieldListVO;
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

    List<ProductionBatch> batchInfoByFinished(@Param("fieldId") int fieldId, @Param("finished") int finished);

    List<ProductionBatch> batchInfoByGenerated(@Param("fieldId") int fieldId, @Param("generated") int generated);

    ProductionBatch onlyBatch(@Param("fieldId") int fieldId, @Param("today") Date today);

    List<Integer> selectFieldIdByUserId(@Param("source") int source, @Param("sourceId") int sourceId);

    List<ProductionBatch> selectByExistProcessRecord(int userId);

    List<ProductionBatch> selectByFieldId(int fieldId);

    List<ProductionBatch> selectByFieldList(List<FieldListVO> fieldListVOList);
}