package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.ProductPool;

public interface ProductPoolMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductPool record);

    int insertSelective(ProductPool record);

    ProductPool selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductPool record);

    int updateByPrimaryKey(ProductPool record);
}