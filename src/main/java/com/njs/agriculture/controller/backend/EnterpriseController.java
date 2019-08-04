package com.njs.agriculture.controller.backend;

import com.alibaba.fastjson.JSONObject;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.pojo.Enterprise;
import com.njs.agriculture.service.IEnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
