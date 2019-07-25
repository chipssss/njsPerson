package com.njs.agriculture.controller.portal;

import com.alibaba.fastjson.JSONObject;
import com.njs.agriculture.VO.InputVO;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.pojo.InputPurchase;
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
}
