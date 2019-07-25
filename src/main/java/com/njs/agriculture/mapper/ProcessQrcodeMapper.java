package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.ProcessQrcode;

public interface ProcessQrcodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProcessQrcode record);

    int insertSelective(ProcessQrcode record);

    ProcessQrcode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProcessQrcode record);

    int updateByPrimaryKey(ProcessQrcode record);
}