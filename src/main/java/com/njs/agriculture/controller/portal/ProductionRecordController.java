package com.njs.agriculture.controller.portal;

import com.alibaba.fastjson.JSONObject;
import com.njs.agriculture.VO.FieldVO;
import com.njs.agriculture.VO.ProcessRecordInfoVO;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.pojo.Field;
import com.njs.agriculture.pojo.ProductionBatch;
import com.njs.agriculture.service.IBatchService;
import com.njs.agriculture.service.IFieldService;
import com.njs.agriculture.service.IProcessRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @Autowired
    private IBatchService iBatchService;
    
    

    @PostMapping("batchInfo.do")
    public ServerResponse batchInfo(@RequestBody JSONObject jsonObject){
        return iBatchService.batchInfo(jsonObject.getIntValue("fieldId"));
    }

    @PostMapping("processRecord.do")
    public ServerResponse processRecord(@RequestBody JSONObject jsonObject){
        int userId = jsonObject.getIntValue("userId");
        String startTime = jsonObject.getString("startTime");
        String endTime = jsonObject.getString("endTime");
        if(startTime == null){
            startTime = "2000-01-01";
        }
        if(endTime == null ){
            // TODO 有可能随年份改变而失效
            endTime = "2050-01-01";
        }
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

    @PostMapping("processRecordAdd.do")
    public ServerResponse processRecordAdd(@RequestBody ProcessRecordInfoVO processRecordInfoVO){
        return iProcessRecordService.addProcess(processRecordInfoVO);
    }

    @PostMapping("processImgUpload.do")
    public ServerResponse processImgUpload(List<MultipartFile> files){
        return iProcessRecordService.processImgUpload(files);
    }


    @PostMapping("batchAdd.do")
    public ServerResponse batchInfo(@RequestBody ProductionBatch productionBatch){
        return iBatchService.batchAdd(productionBatch);
    }

    @GetMapping("batchDel.do")
    public ServerResponse batchInfo(int id){
        return iBatchService.batchDel(id);
    }



}
