package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.SecureImage;

public interface SecureImageMapper {
    int insert(SecureImage record);

    int insertSelective(SecureImage record);
}