package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.ProductStock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductStockMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductStock record);

    int insertSelective(ProductStock record);

    ProductStock selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductStock record);

    int updateByPrimaryKey(ProductStock record);

    List<ProductStock> selectBySource(@Param("source") int source, @Param("sourceId") int sourceId);

    List<ProductStock> selectBySourceAndProductId(@Param("source") int source, @Param("sourceId") int sourceId, @Param("productId") int productId);


}