package com.njs.agriculture.mapper;

import com.njs.agriculture.VO.ApkVO;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.pojo.App;

import java.util.List;

public interface AppMapper {
    int deleteByPrimaryKey(Integer appId);

    int insert(App record);

    int insertSelective(App record);

    App selectByPrimaryKey(Integer appId);

    int updateByPrimaryKeySelective(App record);

    int updateByPrimaryKey(App record);

    ServerResponse updateApk(ApkVO apkVO);

     int inserttoapp(App app);

    App selectByversionCode(Integer versionCode);

    List<App> getLatestApk();
}