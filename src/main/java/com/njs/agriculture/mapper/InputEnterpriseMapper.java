package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.InputEnterprise;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InputEnterpriseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InputEnterprise record);

    int insertSelective(InputEnterprise record);

    InputEnterprise selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InputEnterprise record);

    int updateByPrimaryKey(InputEnterprise record);

    List<InputEnterprise> selectAll(int enterpriseId);

    List<InputEnterprise> selectByCategoryIdList(@Param("enterpriseId") int enterpriseId, @Param("categoryList") List<Integer> categoryList);

    List<InputEnterprise> selectByCategoryId(@Param("enterpriseId") int enterpriseId,@Param("categoryId") int categoryId);

    List<InputEnterprise> getStockByQuantity(@Param("enterpriseId") int enterpriseId, @Param("quantity") int quantity);

}