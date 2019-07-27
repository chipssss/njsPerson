package com.njs.agriculture.controller.portal;

import com.alibaba.fastjson.JSONObject;
import com.njs.agriculture.VO.InputVO;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.service.IInputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/25
 * @Description:
 */
@RestController
@RequestMapping("/portal/input/")
public class InputController {

    @Autowired
    IInputService iInputService;

    @PostMapping("purchase.do")
    public ServerResponse purchase(@RequestBody InputVO inputVO){
        return iInputService.purchase(inputVO);
    }

   @RequestMapping("categoryInfo.do")
    public ServerResponse categoryInfo(){
        return iInputService.categoryInfo();
   }

   @PostMapping("infoGet.do")
    public ServerResponse infoGet(@RequestBody JSONObject jsonObject){
        int firstCateId = jsonObject.getIntValue("firstCateId");
        int secondCateId = jsonObject.getIntValue("secondCateId");
        String orderBy = jsonObject.getString("orderBy");
        int sourceId = jsonObject.getIntValue("sourceId");
        int source = jsonObject.getIntValue("source");
        int pageNum = (int)jsonObject.getOrDefault("pageNum",1);
       int pageSize = (int)jsonObject.getOrDefault("pageSize",10);
       return iInputService.infoGet(firstCateId, secondCateId, orderBy, sourceId, source, pageNum, pageSize);

   }

   @PostMapping("stockRemind.do")
    public ServerResponse stockRemind(@RequestBody JSONObject jsonObject){
       int sourceId = jsonObject.getIntValue("sourceId");
       int source = jsonObject.getIntValue("source");
       int type = jsonObject.getIntValue("type");
       int threshold = jsonObject.getIntValue("threshold");
       return iInputService.stockRemind(sourceId, source, type, threshold);

   }
}
