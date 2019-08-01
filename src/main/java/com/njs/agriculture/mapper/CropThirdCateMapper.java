package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.CropThirdCate;

import java.util.List;

public interface CropThirdCateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CropThirdCate record);

    int insertSelective(CropThirdCate record);

    CropThirdCate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CropThirdCate record);

    int updateByPrimaryKey(CropThirdCate record);

    List<CropThirdCate> selectBySecondCateId(int secondCateId);
}