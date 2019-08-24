package com.njs.agriculture.controller.backend;



import com.alibaba.fastjson.JSONObject;
import com.njs.agriculture.common.Const;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.pojo.User;
import com.njs.agriculture.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/12
 * @Description:
 */
@RestController
@RequestMapping("/backend/user")
public class AdminController {

    @Autowired
    private IUserService iUserSerivce;

    @RequestMapping("usersGet.do")
    public ServerResponse getUsers(@RequestBody(required = false) JSONObject jsonObject){
        int pageNum = (int)jsonObject.getOrDefault("pageNum",1);
        int pageSize = (int)jsonObject.getOrDefault("pageSize",20);
        return iUserSerivce.getUsers(pageNum, pageSize);
    }

    @RequestMapping("positionUpdate.do")
    public ServerResponse positionUpdate(@RequestBody JSONObject jsonObject){
         int id = jsonObject.getIntValue("id");
         int position = jsonObject.getIntValue("position");
         return iUserSerivce.positionUpdate(id, position);
    }

    @GetMapping("userDel.do")
    public ServerResponse userDel(int userId, HttpSession httpSession){
        User user = (User)httpSession.getAttribute(Const.CURRENT_USER);
        if(user.getType() == 1 || user.getType() == 2){
            return ServerResponse.createByErrorMessage("非管理部门!");
        }
        return iUserSerivce.userDel(userId);
    }






}
