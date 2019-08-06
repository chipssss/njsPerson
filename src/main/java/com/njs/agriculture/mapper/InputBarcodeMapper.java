package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.InputBarcode;
import com.njs.agriculture.pojo.InputBarcodeKey;

public interface InputBarcodeMapper {
    int deleteByPrimaryKey(InputBarcodeKey key);

    int insert(InputBarcode record);

    int insertSelective(InputBarcode record);

    InputBarcode selectByPrimaryKey(InputBarcodeKey key);

    int updateByPrimaryKeySelective(InputBarcode record);

    int updateByPrimaryKey(InputBarcode record);

    InputBarcode selectByBarCode(String barCode);
}