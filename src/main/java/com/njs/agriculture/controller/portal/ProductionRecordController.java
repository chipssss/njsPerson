package com.njs.agriculture.controller.portal;

import com.alibaba.fastjson.JSONObject;
import com.njs.agriculture.VO.BatchInfoVO;
import com.njs.agriculture.VO.ProcessRecordInfoVO;
import com.njs.agriculture.common.Const;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.mapper.ProductionBatchMapper;
import com.njs.agriculture.pojo.ProductionBatch;
import com.njs.agriculture.pojo.User;
import com.njs.agriculture.service.IActivationService;
import com.njs.agriculture.service.IBatchService;
import com.njs.agriculture.service.IProcessRecordService;
import com.njs.agriculture.service.IProductService;
import com.njs.agriculture.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Date;
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
    private IActivationService iActivationService;

    @Autowired
    private IProcessRecordService iProcessRecordService;

    @Autowired
    private IBatchService iBatchService;

    @Autowired
    private ProductionBatchMapper productionBatchMapper;

    @Autowired
    IProductService iProductService;
    

    @PostMapping("batchInfoByFinished.do")
    public ServerResponse batchInfoByFinished(@RequestBody JSONObject jsonObject){
        int fieldId = jsonObject.getIntValue("fieldId");
        int finished = jsonObject.getIntValue("finished");
        return iBatchService.batchInfoByFinished(fieldId, finished);
    }

    @PostMapping("batchInfoByGenerated.do")
    public ServerResponse batchInfoByGenerated(@RequestBody JSONObject jsonObject){
        int fieldId = jsonObject.getIntValue("fieldId");
        int generated = jsonObject.getIntValue("generated");
        return iBatchService.batchInfoByGenerated(fieldId, generated);
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
        int fieldId = jsonObject.getIntValue("fieldId");
        int cropId = jsonObject.getIntValue("cropId");
        int pageNum = (int)jsonObject.getOrDefault("pageNum",1);
        int pageSize = (int)jsonObject.getOrDefault("pageSize",20);
        return iProcessRecordService.processRecord(userId, startTime, endTime, fieldId, cropId, pageNum, pageSize);
    }

    @GetMapping("getRecordCrop.do")
    public ServerResponse getRecordCrop(HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        return iProcessRecordService.getRecordCrop(user.getUserId());
    }


    @PostMapping("trace.do")
    public ServerResponse trace(@RequestBody JSONObject jsonObject){
        Date startTime = DateUtil.strToDate(jsonObject.getString("startTime"), DateUtil.SHORT_FORMAT);
        Date endTime = DateUtil.strToDate(jsonObject.getString("endTime"), DateUtil.SHORT_FORMAT);
        if(startTime == null){
            startTime = DateUtil.strToDate("2000-01-01", DateUtil.SHORT_FORMAT);
        }
        if(endTime == null){
            endTime = new Date();
        }
        int pageNum = (int)jsonObject.getOrDefault("pageNum", 1);
        int pageSize = (int)jsonObject.getOrDefault("pageSize", 10);
        return iProcessRecordService.trace(pageNum, pageSize, startTime, endTime, jsonObject.getIntValue("batchId"));
    }

    @PostMapping("traceGenerate.do")
    public ServerResponse generateTrace(@RequestBody JSONObject jsonObject){
        int batchId = jsonObject.getIntValue("batchId");
        ProductionBatch productionBatch = productionBatchMapper.selectByPrimaryKey(batchId);
        if(productionBatch == null){
            return ServerResponse.createByErrorMessage("找不到批次");
        }
        List<Integer> recordIds = jsonObject.getJSONArray("recordIds").toJavaList(Integer.class);
        return iProcessRecordService.generateTrace(batchId, recordIds, jsonObject.getIntValue("quantity"));
    }

    @PostMapping("processRecordAdd.do")
    public ServerResponse processRecordAdd(@RequestBody ProcessRecordInfoVO processRecordInfoVO, HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorMessage("sorry,请登录！");
        }
        return iProcessRecordService.addProcess(processRecordInfoVO, user);
    }

    @PostMapping("processImgUpload.do")
    public ServerResponse processImgUpload(List<MultipartFile> files){
        return iProcessRecordService.processImgUpload(files);
    }

    @PostMapping("batchAdd.do")
    public ServerResponse batchAdd(@RequestBody BatchInfoVO batchInfoVO, HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        return iBatchService.batchAdd(batchInfoVO, user.getUserId());
    }

    @GetMapping("batchDel.do")
    public ServerResponse batchInfo(int id){
        return iBatchService.batchDel(id);
    }

    @GetMapping("operationGet.do")
    public ServerResponse operationGet(){
        return iProcessRecordService.getOperation();
    }

    @GetMapping("getFieldsByBatchExist.do")
    public ServerResponse getFieldsByBatchExist(int userId){
        return iProcessRecordService.getFieldsByBatchExist(userId);
    }

    @GetMapping("getBatchesByExistProcessRecord.do")
    public ServerResponse getBatchesByUserId(int userId){
        return iProcessRecordService.getBatchesByUserId(userId);
    }

    @PostMapping("getRecoveryRecord.do")
    public ServerResponse getRecoveryRecord(@RequestBody JSONObject jsonObject){
        int source = jsonObject.getIntValue("source");
        int sourceId = jsonObject.getIntValue("sourceId");
        return iProcessRecordService.getRecoveryRecord(source, sourceId);
    }

    @PostMapping("getRecordsUngenratedByBatch.do")
    public ServerResponse getRecordUngenerated(@RequestBody JSONObject jsonObject, HttpSession httpSession){
        User user = (User)httpSession.getAttribute(Const.CURRENT_USER);
        int batchId = jsonObject.getIntValue("batchId");
        int pageNum = (int)jsonObject.getOrDefault("pageNum", 1);
        int pageSize = (int)jsonObject.getOrDefault("pageSize", 10);
        return iProcessRecordService.getRecordsUngenratedByBatch(batchId, user.getUserId(), pageNum, pageSize);
    }

    @PostMapping("getRecordsUngenratedByField.do")
    public ServerResponse getRecordsUngenratedByField(@RequestBody JSONObject jsonObject, HttpSession httpSession){
        User user = (User)httpSession.getAttribute(Const.CURRENT_USER);
        int fieldId = jsonObject.getIntValue("fieldId");
        int pageNum = (int)jsonObject.getOrDefault("pageNum", 1);
        int pageSize = (int)jsonObject.getOrDefault("pageSize", 10);
        return iProcessRecordService.getRecordsUngenratedByField(fieldId, user.getUserId(), pageNum, pageSize);
    }

    @GetMapping("getBatchesFinished.do")
    public ServerResponse getBatchesFinished(HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        return iBatchService.getBatchesFinishedOrGenerated(user.getUserId(), 0);
    }

    @GetMapping("getBatchesGenerated.do")
    public ServerResponse getBatchesGenerated(HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        return iBatchService.getBatchesFinishedOrGenerated(user.getUserId(), 1);
    }

    @GetMapping("getBatchesStream.do")
    public ServerResponse getBatchesStream(HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        return iBatchService.getBatchesStream(user.getUserId());
    }

    @GetMapping("path")
    public JSONObject checkActivation(String id){
        return iActivationService.checkActivation(id);
    }

    @GetMapping("getActivationStream.do")
    public ServerResponse getActivationStream(HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        return iActivationService.getActivationStream(user.getUserId());
    }

    @PostMapping("bindProduct.do")
    public ServerResponse bindProduct(@RequestBody JSONObject jsonObject, HttpSession session){
        String code = jsonObject.getString("code");
        String batchId = jsonObject.getString("batchId");
        String productName = jsonObject.getString("productName");
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        return iActivationService.bindProduct(code, batchId, user.getUserId(), productName);
    }

    @GetMapping("getAllBatchInfo.do")
    public ServerResponse getAllBatchInfo(HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        return iProductService.getAllBatchInfo(user.getUserId());
    }



}
