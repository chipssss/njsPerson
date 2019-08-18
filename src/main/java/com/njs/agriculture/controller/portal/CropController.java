package com.njs.agriculture.controller.portal;

import com.alibaba.fastjson.JSONObject;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.service.ICropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("cropGet.do")
    public ServerResponse cropGet(@RequestBody JSONObject jsonObject){
        int pageNum = (int)jsonObject.getOrDefault("pageNum",1);
        int pageSize = (int)jsonObject.getOrDefault("pageSize",10);
        return iCropService.cropGet(pageNum, pageSize);
    }

    @GetMapping("cropGet.do")
    public ServerResponse cropGetAndroid(){
        return iCropService.cropGetAndroid();
    }

    @GetMapping("cropSecondCateGet.do")
    public ServerResponse cropSecondCateGet(){
        return iCropService.cropSecondCateGet();
    }

    @GetMapping("cropFirstCateGet.do")
    public ServerResponse cropFirstCateGet(){
        return iCropService.cropFirstCateGet();
    }

    @GetMapping("cropThirdCateGet.do")
    public ServerResponse cropThirdCateGet(){
        return iCropService.cropThirdCateGet();
    }


    @PostMapping("cropDel.do")
    public ServerResponse cropDel(@RequestBody JSONObject jsonObject){
        int id = jsonObject.getIntValue("id");
        int flag = jsonObject.getIntValue("flag");
        return iCropService.cropDel(id, flag);
    }

    @PostMapping("cropCateAdd.do")
    public ServerResponse inputCateAdd(@RequestBody JSONObject jsonObject){
        int type = jsonObject.getIntValue("type");
        String name = jsonObject.getString("name");
        int superiorId = jsonObject.getIntValue("superiorId");
        return iCropService.cropCateAdd(type, name, superiorId);
    }


}
