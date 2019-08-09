package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.ProductionSecondCate;

import java.util.List;

public interface ProductionSecondCateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductionSecondCate record);

    int insertSelective(ProductionSecondCate record);

    ProductionSecondCate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductionSecondCate record);

    int updateByPrimaryKey(ProductionSecondCate record);

    List<ProductionSecondCate> selectByFirstCateId(int firstCateId);

    List<ProductionSecondCate> selectAll();
}