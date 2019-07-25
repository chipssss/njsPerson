package com.njs.agriculture.controller.portal;

import com.alibaba.fastjson.JSONObject;
import com.njs.agriculture.common.Const;
import com.njs.agriculture.common.ServerResponse;

import com.njs.agriculture.pojo.User;
import com.njs.agriculture.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;
import java.util.Map;

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





}
