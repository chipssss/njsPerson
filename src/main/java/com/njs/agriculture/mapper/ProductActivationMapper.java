package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.ProductActivation;

import java.util.List;

public interface ProductActivationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductActivation record);

    int insertSelective(ProductActivation record);

    ProductActivation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductActivation record);

    int updateByPrimaryKey(ProductActivation record);

    List<ProductActivation> getByUserId(int userId);

    int checkByCode(String  code);

    ProductActivation getByCode(String code);
}