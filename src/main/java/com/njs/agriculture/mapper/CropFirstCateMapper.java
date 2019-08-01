package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.CropFirstCate;

import java.util.List;

public interface CropFirstCateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CropFirstCate record);

    int insertSelective(CropFirstCate record);

    CropFirstCate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CropFirstCate record);

    int updateByPrimaryKey(CropFirstCate record);

    List<CropFirstCate> selectAll();
}