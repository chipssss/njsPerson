package com.njs.agriculture.controller.portal;

import com.alibaba.fastjson.JSONObject;
import com.njs.agriculture.common.Const;
import com.njs.agriculture.common.ServerResponse;

import com.njs.agriculture.pojo.User;
import com.njs.agriculture.service.IFileService;
import com.njs.agriculture.service.IUserService;
import com.njs.agriculture.utils.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/11
 * @Description:
 */
@RestController
@RequestMapping("/portal/user/")
public class CommonController {

    @Autowired
    private IUserService iUserService;

    @GetMapping("checkNum.do")
    public ServerResponse checkNum(String number){
        return iUserService.checkNum(number);
    }


    @RequestMapping("apply.do")
    public ServerResponse apply(@RequestBody User user){
        return iUserService.apply(user);
    }

    @RequestMapping("login.do")
    public ServerResponse<Map> login(@RequestBody JSONObject jsonObject, HttpSession session){
        String phonenum = jsonObject.getString("phonenum");
        String password = jsonObject.getString("password");
        ServerResponse<Map> response = iUserService.login(phonenum, password);
        if(response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;

    }

    @RequestMapping("info.do")
    public ServerResponse<User> info(@RequestBody JSONObject jsonObject){
        return null;
    }

    @RequestMapping("upload.do")
    public ServerResponse upload(MultipartFile file){
        return iUserService.upload(file);
    }

    @RequestMapping("passwordChang.do")
    public ServerResponse passwordChang(@RequestBody JSONObject jsonObject){
        String phoneNum = jsonObject.getString("phoneNum");
        String oldPassword = jsonObject.getString("oldPassword");
        String newPassword = jsonObject.getString("newPassword");
        return iUserService.passwordChange(phoneNum, oldPassword, newPassword);
    }

    @RequestMapping("updateInfo.do")
    public ServerResponse updateInfo(@RequestBody JSONObject jsonObject){
        String key = jsonObject.getString("key");
        String value = jsonObject.getString("value");
        int userId = jsonObject.getIntValue("userId");
        return iUserService.updateInfo(key, value, userId);
    }





}
