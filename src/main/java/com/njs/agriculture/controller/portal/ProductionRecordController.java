package com.njs.agriculture.controller.portal;

import com.alibaba.fastjson.JSONObject;
import com.njs.agriculture.VO.FieldVO;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.pojo.Field;
import com.njs.agriculture.service.IProcessRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/28
 * @Description:
 */
@RestController
@Slf4j
@RequestMapping("/portal/processRecord/")
public class ProductionRecordController {

    @Autowired
    private IProcessRecordService iProcessRecordService;

    @PostMapping("fieldAdd.do")
    public ServerResponse addField(@RequestBody FieldVO fieldVO){
        return iProcessRecordService.addField(fieldVO);
    }

    @PostMapping("fieldDel.do")
    public ServerResponse delField(@RequestBody JSONObject jsonObject){
        int fieldId = jsonObject.getIntValue("fieldId");
        return iProcessRecordService.delField(fieldId);
    }

    @PostMapping("fieldModify.do")
    public ServerResponse modifyField(@RequestBody Field field){
        return iProcessRecordService.modifyField(field);
    }

    @PostMapping("fieldInfo.do")
    public ServerResponse fieldInfo(@RequestBody JSONObject jsonObject){
        return iProcessRecordService.fieldInfo(jsonObject.getIntValue("userId"));
    }

    @PostMapping("batchInfo.do")
    public ServerResponse batchInfo(@RequestBody JSONObject jsonObject){
        return iProcessRecordService.batchInfo(jsonObject.getIntValue("fieldId"));
    }

    @PostMapping("processRecord.do")
    public ServerResponse processRecord(@RequestBody JSONObject jsonObject){
        int userId = jsonObject.getIntValue("userId");
        String startTime = jsonObject.getString("startTime");
        String endTime = jsonObject.getString("endTime");
        int batchId = jsonObject.getIntValue("batchId");
        int cropId = jsonObject.getIntValue("cropId");
        int pageNum = (int)jsonObject.getOrDefault("pageNum",1);
        int pageSize = (int)jsonObject.getOrDefault("pageSize",20);
        return iProcessRecordService.processRecord(userId, startTime, endTime, batchId, cropId, pageNum, pageSize);
    }


    @PostMapping("trace.do")
    public ServerResponse trace(@RequestBody JSONObject jsonObject){
        return iProcessRecordService.trace(jsonObject.getIntValue("qrcodeId"));
    }

    @PostMapping("traceGenerate.do")
    public ServerResponse generateTrace(@RequestBody JSONObject jsonObject){
        return iProcessRecordService.generateTrace(jsonObject.getJSONArray("recordIds").toJavaList(Integer.class));
    }




}
