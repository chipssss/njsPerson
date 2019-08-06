package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.ProductionThirdCate;

import java.util.List;

public interface ProductionThirdCateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductionThirdCate record);

    int insertSelective(ProductionThirdCate record);

    ProductionThirdCate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductionThirdCate record);

    int updateByPrimaryKey(ProductionThirdCate record);

    List<ProductionThirdCate> selectBySecondCateId(int secondCateId);
}