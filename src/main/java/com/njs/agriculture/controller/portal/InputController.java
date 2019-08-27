package com.njs.agriculture.controller.portal;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.njs.agriculture.VO.InputVO;
import com.njs.agriculture.common.Const;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.pojo.InputConsume;
import com.njs.agriculture.pojo.User;
import com.njs.agriculture.service.IInputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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
    public ServerResponse purchase(@RequestBody InputVO inputVO, HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorMessage("请登录！");
        }
        inputVO.setUserId(user.getUserId());
        return iInputService.purchase(inputVO);
    }

   @PostMapping("categoryInfo.do")
    public ServerResponse categoryInfo(@RequestBody JSONObject jsonObject){
       int pageNum = (int)jsonObject.getOrDefault("pageNum",1);
       int pageSize = (int)jsonObject.getOrDefault("pageSize",10);
        return iInputService.categoryInfo(pageNum, pageSize);
   }

    @GetMapping("categoryInfo.do")
    public ServerResponse categoryInfoForAndroid(){
        return iInputService.categoryInfoForAndroid();
    }

   @GetMapping("firstCateGet")
   public ServerResponse firstCateGet(){
       return iInputService.firstCateGet();
   }

    @GetMapping("secondCateGet")
    public ServerResponse secondCateGet(){
        return iInputService.secondCateGet();
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

   @PostMapping("sumGet.do")
   public ServerResponse sumGet(@RequestBody JSONObject jsonObject){
        int source = jsonObject.getIntValue("source");
        int sourceId = jsonObject.getIntValue("sourceId");
        return iInputService.sumGet(source, sourceId);
   }

   @PostMapping("stockRemind.do")
    public ServerResponse stockRemind(@RequestBody JSONObject jsonObject){
       int sourceId = jsonObject.getIntValue("sourceId");
       int source = jsonObject.getIntValue("source");
       int type = jsonObject.getIntValue("type");
       int threshold = jsonObject.getIntValue("threshold");
       return iInputService.stockRemind(sourceId, source, type, threshold);

   }

    @PostMapping("inputReturn.do")
    public ServerResponse inputReturn(@RequestBody JSONObject jsonObject){
        return iInputService.returnInput(jsonObject.getIntValue("id"), jsonObject.getFloatValue("quantity"));
    }

    @GetMapping("scanBarcode.do")
    public ServerResponse barcodeScan(String barcode){
        return iInputService.scanBarcode(barcode);
    }

    @PostMapping("recordGet.do")
    public ServerResponse recordGet(@RequestBody JSONObject jsonObject){
        int source = jsonObject.getIntValue("source");
        int sourceId = jsonObject.getIntValue("sourceId");
        int type = jsonObject.getIntValue("type");
        return iInputService.inputRecord(source, sourceId, type);
    }

    @PostMapping("inputDel.do")
    public ServerResponse inputDel(@RequestBody JSONObject jsonObject){
        int id = jsonObject.getIntValue("id");
        int flag = jsonObject.getIntValue("flag");
        int source = jsonObject.getIntValue("source");
        return iInputService.inputDel(id, flag, source);
    }

    @PostMapping("inputCateAdd.do")
    public ServerResponse inputCateAdd(@RequestBody JSONObject jsonObject){
        int type = jsonObject.getIntValue("type");
        String name = jsonObject.getString("name");
        int superiorId = jsonObject.getIntValue("superiorId");
        return iInputService.inputCateAdd(type, name, superiorId);
    }

    @PostMapping("inputConsume.do")
    public ServerResponse inputConsume(@RequestBody InputConsume inputConsume, HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        return iInputService.inputConsume(inputConsume, user.getUserId());
    }

    @GetMapping("inputConsumeList.do")
    public ServerResponse inputConsumeList(HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        return iInputService.inputConsumeList(user.getUserId());
    }

    @PostMapping("inputConsumeReview.do")
    public ServerResponse inputConsumeReview(@RequestBody JSONObject jsonObject, HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        int id = jsonObject.getIntValue("id");
        int status = jsonObject.getIntValue("status");
        return iInputService.inputConsumeReview(id, status, user.getUserId());
    }
}
