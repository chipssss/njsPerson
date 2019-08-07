package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.InputPurchase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InputPurchaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InputPurchase record);

    int insertSelective(InputPurchase record);

    InputPurchase selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InputPurchase record);

    int updateByPrimaryKey(InputPurchase record);

    List<InputPurchase> selectBySource(@Param("inputSource") int inputSource, @Param("sourceId") int sourceId);
}