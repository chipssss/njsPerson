package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.UserType;

public interface UserTypeMapper {
    int insert(UserType record);

    int insertSelective(UserType record);
}