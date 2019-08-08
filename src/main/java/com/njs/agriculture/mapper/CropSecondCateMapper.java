package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.CropSecondCate;

import java.util.List;

public interface CropSecondCateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CropSecondCate record);

    int insertSelective(CropSecondCate record);

    CropSecondCate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CropSecondCate record);

    int updateByPrimaryKey(CropSecondCate record);

    List<CropSecondCate> selectByFirstCateId(int firstCateId);

    List<CropSecondCate> selectAll();
}