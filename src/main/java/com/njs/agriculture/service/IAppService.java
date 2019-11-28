package com.njs.agriculture.service;

import com.njs.agriculture.VO.ApkVO;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.pojo.App;
import org.springframework.web.multipart.MultipartFile;

public interface IAppService {
    int deleteByPrimaryKey(Integer appId);

    int insert(App record);

    int insertSelective(App record);

    App selectByPrimaryKey(Integer appId);

    int updateByPrimaryKeySelective(App record);

    int updateByPrimaryKey(App record);

    ServerResponse getLatestApk(Integer versionCode);

   App selectByversionCode(Integer versionCode);

    ServerResponse updateApk(Integer versionCode,String declare,MultipartFile file);
}
