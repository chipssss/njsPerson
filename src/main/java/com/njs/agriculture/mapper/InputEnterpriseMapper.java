package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.InputEnterprise;

public interface InputEnterpriseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InputEnterprise record);

    int insertSelective(InputEnterprise record);

    InputEnterprise selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InputEnterprise record);

    int updateByPrimaryKey(InputEnterprise record);
}