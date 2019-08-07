package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.InputConsume;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InputConsumeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InputConsume record);

    int insertSelective(InputConsume record);

    InputConsume selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InputConsume record);

    int updateByPrimaryKey(InputConsume record);

    List<InputConsume> selectByEnterpriseId(int enterpriseId);

    List<InputConsume> selectByUserId(int userId);
}