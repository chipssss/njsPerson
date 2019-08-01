package com.njs.agriculture.controller.portal;

import com.alibaba.fastjson.JSONObject;
import com.njs.agriculture.VO.FieldVO;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.pojo.Field;
import com.njs.agriculture.service.IFieldService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/30
 * @Description:
 */
@RestController
@Slf4j
@RequestMapping("/portal/field/")
public class FieldController {
    @Autowired
    private IFieldService iFieldService;

    @PostMapping("fieldAdd.do")
    public ServerResponse addField(@RequestBody FieldVO fieldVO){
        return iFieldService.addField(fieldVO);
    }

    @PostMapping("fieldDel.do")
    public ServerResponse delField(@RequestBody JSONObject jsonObject){
        int fieldId = jsonObject.getIntValue("fieldId");
        return iFieldService.delField(fieldId);
    }

    @PostMapping("fieldModify.do")
    public ServerResponse modifyField(@RequestBody FieldVO fieldVO){
        return iFieldService.modifyField(fieldVO);
    }

    @PostMapping("fieldInfo.do")
    public ServerResponse fieldInfo(@RequestBody JSONObject jsonObject){
        return iFieldService.fieldInfo(jsonObject.getIntValue("userId"));
    }
}
