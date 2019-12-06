package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.SecureImage;

import java.util.List;

public interface SecureImageMapper {
    int insert(SecureImage record);

    int insertSelective(SecureImage record);

    List<String> selectById(Integer id);
}