package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.ProductOut;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductOutMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductOut record);

    int insertSelective(ProductOut record);

    ProductOut selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductOut record);

    int updateByPrimaryKey(ProductOut record);

    List<ProductOut> selectBySource(@Param("source") int source, @Param("sourceId") int sourceId);

    List<ProductOut> selectByProductId(int productId);
}