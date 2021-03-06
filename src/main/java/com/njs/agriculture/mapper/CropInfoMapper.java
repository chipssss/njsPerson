package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.CropInfo;

import java.util.List;

public interface CropInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CropInfo record);

    int insertSelective(CropInfo record);

    CropInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CropInfo record);

    int updateByPrimaryKey(CropInfo record);

    List<CropInfo> selectByCateId(int id);

    List<CropInfo> selectAll();

    List<CropInfo> selectByCateIdOrUserId(int cateId, int userId);
}