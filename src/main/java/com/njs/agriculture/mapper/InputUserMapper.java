package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.InputUser;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface InputUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InputUser record);

    int insertSelective(InputUser record);

    InputUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InputUser record);

    int updateByPrimaryKey(InputUser record);

    List<InputUser> selectAll(int userId);

    List<InputUser> selectByCategoryIdList(@Param("userId") int userId, @Param("categoryList") List<Integer> categoryList);

    List<InputUser> selectByCategoryId(@Param("userId") int userId, @Param("categoryId") int categoryId);

    List<InputUser> getStockByQuantity(@Param("userId") int userId, @Param("quantity") int quantity);

}