package com.njs.agriculture.controller.backend;

import com.alibaba.fastjson.JSONObject;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.pojo.ServicePool;
import com.njs.agriculture.service.IServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/8/3
 * @Description:
 */
@RestController
@RequestMapping("/backend/service/")
public class ServiceController {

    @Autowired
    IServiceService iServiceService;

    @RequestMapping("serviceApply.do")
    public ServerResponse serviceApply(@RequestBody ServicePool servicePool){
        return iServiceService.serviceApply(servicePool);
    }

    @RequestMapping("serviceListGet.do")
    public ServerResponse serviceListGet(){
        return iServiceService.serviceListGet();
    }

    @RequestMapping("serviceInfoGet.do")
    public ServerResponse serviceInfoGet(@RequestBody JSONObject jsonObject){
        int flag = jsonObject.getIntValue("flag");
        int sourceId = jsonObject.getIntValue("sourceId");
        return iServiceService.serviceInfoGet(flag, sourceId);
    }

    @RequestMapping("serviceApplyRecord.do")
    public ServerResponse serviceApplyRecord(@RequestBody JSONObject jsonObject){
        return iServiceService.serviceApplyRecord(jsonObject.getIntValue("userId"));
    }

    @RequestMapping("applyerGet.do")
    public ServerResponse applyerGet(@RequestBody JSONObject jsonObject){
        return iServiceService.applyerGet(jsonObject.getIntValue("status"));
    }

    @RequestMapping("serviceUpdate.do")
    public ServerResponse serviceUpdate(@RequestBody JSONObject jsonObject){
        int id = jsonObject.getIntValue("id");
        int status = jsonObject.getIntValue("status");
        String reply = jsonObject.getString("reply");
        return iServiceService.serviceUpdate(id, status, reply);
    }

}
