package com.njs.agriculture.mapper;

import com.njs.agriculture.pojo.App;

import java.util.List;

public interface AppMapper {
    int deleteByPrimaryKey(Integer appId);

    int insert(App record);

    int insertSelective(App record);

    App selectByPrimaryKey(Integer appId);

    int updateByPrimaryKeySelective(App record);
     List<App> getLatestApk();
    App selectByversionCode(Integer versionCode);
    int updateByPrimaryKey(App record);
}