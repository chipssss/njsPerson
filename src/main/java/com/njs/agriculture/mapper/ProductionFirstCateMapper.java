package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.ProductionFirstCate;

import java.util.List;

public interface ProductionFirstCateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductionFirstCate record);

    int insertSelective(ProductionFirstCate record);

    ProductionFirstCate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductionFirstCate record);

    int updateByPrimaryKey(ProductionFirstCate record);

    List<ProductionFirstCate> selectAll();
}