package com.njs.agriculture.service.impl;

import com.njs.agriculture.VO.ApkVO;
import com.njs.agriculture.common.Const;
import com.njs.agriculture.common.ResponseCode;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.mapper.AppMapper;
import com.njs.agriculture.pojo.App;
import com.njs.agriculture.service.IAppService;
import com.njs.agriculture.service.IFileService;
import com.njs.agriculture.utils.DateUtil;
import com.njs.agriculture.utils.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @Auther: cdl
 * @Date: 2019/11/27
 * @Description:
 */
@Service("IAppService")
public class AppServiceImpl implements IAppService {

    @Autowired
    IFileService iFileService;
    @Autowired
    AppMapper appMapper;
    public int insertintoapp(App app){
        return appMapper.inserttoapp(app);
    }
    @Override
    public int deleteByPrimaryKey(Integer appId) {
        return appMapper.deleteByPrimaryKey(appId);
    }

    @Override
    public int insert(App record) {
        return appMapper.insert(record);
    }

    @Override
    public int insertSelective(App record) {
        return appMapper.insertSelective(record);
    }

    @Override
    public App selectByPrimaryKey(Integer appId) {
        return appMapper.selectByPrimaryKey(appId);
    }

    @Override
    public int updateByPrimaryKeySelective(App record) {
        return appMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(App record) {
        return appMapper.updateByPrimaryKey(record);
    }

@Override
public App selectByversionCode(Integer versionCode){

    return appMapper.selectByversionCode(versionCode);
}
@Override
   public ServerResponse updateApk(ApkVO apkVO)  {
        boolean flag=true;
        try {
            if (null != appMapper.selectByversionCode(apkVO.getVersionCode())) {
                flag=false;
            }
        else{    App app = new App();
            String path = PropertiesUtil.getProperty("appuploadDir-test") + "user";
            String fileName = iFileService.upload(apkVO.getFile(), path);
            app.setFile(Const.USERAPKREEFIX + fileName);
            app.setCreateTime((new Date()));
            app.setDelcare(apkVO.getDelcare());
            app.setVersionCode(apkVO.getVersionCode());

            appMapper.insert(app);
            flag=true;
        }

        }
        catch(Exception e){
            flag=false;
            e.printStackTrace();
        }
    if(flag==false)  return ServerResponse.createByErrorMessage("版本号已存在");
    else  return ServerResponse.createBySuccess("成功");
}
    @Override
    public ServerResponse getLatestApk(Integer versionCode){
        App latestApk=null;
      try {
          List<App> apps= appMapper.getLatestApk();
          latestApk=apps.get(0);
            if(apps.get(0).getVersionCode()==versionCode){
                return ServerResponse.createByErrorCodeMessage(1,"已经为最新版本");
            }

      }catch (Exception e)
      {e.printStackTrace();}
      Map map=new HashMap();
      map.put("versionCode",latestApk.getVersionCode());
      map.put("url",PropertiesUtil.getProperty("server")+(latestApk.getFile()));
      map.put("createTime",latestApk.getCreateTime());
      map.put("declare",latestApk.getDelcare());
        return ServerResponse.createBySuccess(map);
    }

}
