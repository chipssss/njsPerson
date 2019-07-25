package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.InputSecondCate;

import java.util.List;

public interface InputSecondCateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InputSecondCate record);

    int insertSelective(InputSecondCate record);

    InputSecondCate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InputSecondCate record);

    int updateByPrimaryKey(InputSecondCate record);

    List<InputSecondCate> selectByFirstCate(int firstCateId);
}