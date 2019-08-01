package com.njs.agriculture.controller.portal;

import com.alibaba.fastjson.JSONObject;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.service.ICropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/8/1
 * @Description:
 */
@RestController
@RequestMapping("/portal/crop")
public class CropController {

    @Autowired
    ICropService iCropService;

    @RequestMapping("cropAdd.do")
    public ServerResponse cropAdd(@RequestBody JSONObject jsonObject){
        int userId = jsonObject.getIntValue("userId");
        String name = jsonObject.getString("name");
        int typeId = jsonObject.getIntValue("typeId");
        return iCropService.cropAdd(userId, name, typeId);
    }

    @GetMapping("cropGet.do")
    public ServerResponse cropGet(){
        return iCropService.cropGet();
    }


}
