package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.ProductStock;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface ProductStockMapper {
    @CacheEvict(cacheNames = "productStock", key = "#id")
    int deleteByPrimaryKey(Integer id);

    int insert(ProductStock record);

    int insertSelective(ProductStock record);

    @Cacheable(cacheNames = "productStock", key = "#id")
    ProductStock selectByPrimaryKey(Integer id);

    @CacheEvict(cacheNames = "productStock", key = "#record.getId()")
    int updateByPrimaryKeySelective(ProductStock record);

    @CacheEvict(cacheNames = "productStock", key = "#record.getId()")
    int updateByPrimaryKey(ProductStock record);

    List<ProductStock> selectBySource(@Param("source") int source, @Param("sourceId") int sourceId);

    List<ProductStock> selectBySourceAndProductId(@Param("source") int source, @Param("sourceId") int sourceId, @Param("productId") int productId);

    List<ProductStock> selectByProductId(int productId);


}