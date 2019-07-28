package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.ProcessQrcode;

import java.util.List;

public interface ProcessQrcodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProcessQrcode record);

    int insertSelective(ProcessQrcode record);

    ProcessQrcode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProcessQrcode record);

    int updateByPrimaryKey(ProcessQrcode record);

    List<Integer> selectByQrcodeId(int qrcodeId);
}