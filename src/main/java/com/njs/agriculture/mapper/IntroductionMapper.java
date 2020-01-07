package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.Introduction;

import java.util.List;

public interface IntroductionMapper {
    int insert(Introduction record);

    int insertSelective(Introduction record);

    void update(Introduction introduction);

    List<Introduction> selectAll();
}