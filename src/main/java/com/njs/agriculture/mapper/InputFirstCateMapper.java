package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.InputFirstCate;

import java.util.List;

public interface InputFirstCateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InputFirstCate record);

    int insertSelective(InputFirstCate record);

    InputFirstCate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InputFirstCate record);

    int updateByPrimaryKey(InputFirstCate record);

    List<InputFirstCate> selectAll();
}