package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.InputReturn;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InputReturnMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InputReturn record);

    int insertSelective(InputReturn record);

    InputReturn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InputReturn record);

    int updateByPrimaryKey(InputReturn record);

    List<InputReturn> selectByEnterpriseId(int enterpriseId);

    List<InputReturn> selectByUserId(int userId);
}