package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.CropThirdCate;

public interface CropThirdCateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CropThirdCate record);

    int insertSelective(CropThirdCate record);

    CropThirdCate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CropThirdCate record);

    int updateByPrimaryKey(CropThirdCate record);
}