package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.ProductBasic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductBasicMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductBasic record);

    int insertSelective(ProductBasic record);

    ProductBasic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductBasic record);

    int updateByPrimaryKey(ProductBasic record);

    List<ProductBasic> selectBySource(@Param("source") int source, @Param("sourceId") int sourceId);

    List<ProductBasic> selectByProductType(String productType);

    /**
     * 慎用
     * @return
     */
    List<ProductBasic> selectAll();
}