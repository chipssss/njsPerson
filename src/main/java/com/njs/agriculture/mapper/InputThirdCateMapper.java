package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.InputThirdCate;

import java.util.List;

public interface InputThirdCateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InputThirdCate record);

    int insertSelective(InputThirdCate record);

    InputThirdCate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InputThirdCate record);

    int updateByPrimaryKey(InputThirdCate record);

    List<InputThirdCate>  selectBySecondCateId(Integer secondCateId);

    List<InputThirdCate> selectAll();
}