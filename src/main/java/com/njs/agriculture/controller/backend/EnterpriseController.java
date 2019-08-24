package com.njs.agriculture.controller.backend;

import com.alibaba.fastjson.JSONObject;
import com.njs.agriculture.common.Const;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.pojo.Enterprise;
import com.njs.agriculture.pojo.User;
import com.njs.agriculture.pojo.UserRelationship;
import com.njs.agriculture.service.IEnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/8/2
 * @Description:
 */
@RestController
@RequestMapping("/backend/enterprise/")
public class EnterpriseController {

    @Autowired
    private IEnterpriseService iEnterpriseService;

    @RequestMapping("enterpriseAdd.do")
    public ServerResponse enterpriesAdd(@RequestBody Enterprise enterprise){
        return iEnterpriseService.enterpriseAdd(enterprise);
    }

    @PostMapping("enterpriseGet.do")
    public ServerResponse enterpriseGet(@RequestBody JSONObject jsonObject){
        int status = jsonObject.getIntValue("status");
        int pageNum = (int)jsonObject.getOrDefault("pageNum",1);
        int pageSize = (int)jsonObject.getOrDefault("pageSize",10);
        return iEnterpriseService.enterpriseGet(status, pageNum, pageSize);
    }

    @GetMapping("personnelGet.do")
    public ServerResponse personnelGet(int enterpriseId){
        return iEnterpriseService.personnelGet(enterpriseId);
    }

    @PostMapping("enterpriseExamine.do")
    public ServerResponse enterpriseExamine(@RequestBody JSONObject jsonObject){
        int status = jsonObject.getIntValue("status");
        int enterpriseId = jsonObject.getIntValue("enterpriseId");
        return iEnterpriseService.enterpriseExamine(status, enterpriseId);
    }

    @PostMapping("upload.do")
    public ServerResponse upload(MultipartFile file){
        return iEnterpriseService.upload(file);
    }

    @PostMapping("enterpriseUpdate.do")
    public ServerResponse enterpriseUpdate(@RequestBody Enterprise enterprise){
        return iEnterpriseService.enterpriseUpdate(enterprise);
    }

    @GetMapping("enterpriseDel.do")
    public ServerResponse enterpriseDel(int enterpriseId, HttpSession httpSession){
        User user = (User)httpSession.getAttribute(Const.CURRENT_USER);//TODO 不安全，纵向越权
        return iEnterpriseService.enterpriseDel(enterpriseId);
    }

    @PostMapping("enterpriseJoin.do")
    public ServerResponse enterpriseJoin(@RequestBody UserRelationship userRelationship, HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        userRelationship.setUserId(user.getUserId());
        userRelationship.setStatus(0);
        return iEnterpriseService.enterpriseJoin(userRelationship);
    }


}
