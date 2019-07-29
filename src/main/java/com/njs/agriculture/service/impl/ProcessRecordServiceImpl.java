package com.njs.agriculture.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.njs.agriculture.VO.FieldVO;
import com.njs.agriculture.VO.ProcessRecordVO;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.mapper.*;
import com.njs.agriculture.pojo.*;
import com.njs.agriculture.service.IProcessRecordService;
import com.njs.agriculture.utils.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/28
 * @Description:
 */
@Service("iIFieldService")
public class ProcessRecordServiceImpl implements IProcessRecordService {

    @Autowired
    private UserRelationshipMapper userRelationshipMapper;

    @Autowired
    private FieldMapper fieldMapper;

    @Autowired
    private ProductionBatchMapper productionBatchMapper;

    @Autowired
    private ProcessRecordMapper processRecordMapper;

    @Autowired
    private ProcessImageMapper processImageMapper;

    @Autowired
    private QrcodeMapper qrcodeMapper;

    @Autowired
    private ProcessQrcodeMapper processQrcodeMapper;

    @Override
    public ServerResponse addField(FieldVO fieldVO) {
        Field field = new Field();
        BeanUtils.copyProperties(fieldVO, field);
        if(fieldVO.isPerson()){
            field.setSource(0);
            field.setSourceId(fieldVO.getUserId());
        }else {
            ServerResponse<UserRelationship> serverResponse = isAdmin(fieldVO.getUserId());
            if(!serverResponse.isSuccess()){
                return serverResponse;
            }
            field.setSource(1);
            field.setSourceId(serverResponse.getData().getEnterpriseId());
        }
        if(fieldVO.isFree()){
            field.setStatus(0);
        }else {
            field.setStatus(1);
        }
        int resultCount = fieldMapper.insert(field);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("插入田块信息失败！");
        }
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse delField(int fieldId) {
        int resultCount = fieldMapper.deleteByPrimaryKey(fieldId);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("删除田块失败！");
        }
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse modifyField(Field field) {
        int resultCount = fieldMapper.updateByPrimaryKeySelective(field);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("更新田块失败！");
        }
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse fieldInfo(int userId) {
        //1.先判断是否负责人
        ServerResponse<UserRelationship> serverResponse = isAdmin(userId);
        List<Field> fields = Lists.newArrayList();
        if(!serverResponse.isSuccess()){
            //用户id查询
            fields = fieldMapper.selectBySourceId(userId);
        }else{
            fields = fieldMapper.selectBySourceId(serverResponse.getData().getEnterpriseId());
        }
        return ServerResponse.createBySuccess(fields);
    }

    @Override
    public ServerResponse batchInfo(int fieldId) {
        List<ProductionBatch> productionBatches = productionBatchMapper.batchInfo(fieldId);
        return ServerResponse.createBySuccess(productionBatches);
    }

    @Override
    public ServerResponse processRecord(int userId, String startTime, String endTime, int batchId, int cropId, int pageNum, int pageSize){
        Date sTime = DateUtil.strToDate(startTime, DateUtil.SHORT_FORMAT);
        Date eTime = DateUtil.strToDate(endTime, DateUtil.SHORT_FORMAT);
        ServerResponse<UserRelationship> serverResponse = isAdmin(userId);
        List<ProcessRecordVO> processRecords = Lists.newArrayList();
        List<ProcessRecord> processRecordList = Lists.newArrayList();
        PageHelper.startPage(pageNum, pageSize);
        PageHelper.orderBy("source_id, create_time desc");
        if(serverResponse.isSuccess()){
            processRecordList = processRecordMapper.selectByCondition(sTime, eTime, batchId, cropId, serverResponse.getData().getEnterpriseId());
        }else {
            processRecordList = processRecordMapper.selectByCondition(sTime, eTime, batchId, cropId, userId);
        }
        if(!processRecordList.isEmpty()){
            for (ProcessRecord processRecord : processRecordList) {
                ProcessRecordVO processRecordVO = new ProcessRecordVO();
                BeanUtils.copyProperties(processRecord, processRecordVO);
                List<String> images = processImageMapper.selectByRecordId(processRecord.getId());
                processRecordVO.setImages(images);
                processRecords.add(processRecordVO);
            }
        }
        return ServerResponse.createBySuccess(processRecords);
    }

    @Override
    public ServerResponse trace(int qrcodeId) {
        //1.先查List<int> 生产记录的id
        List<Integer> recordIds = processQrcodeMapper.selectByQrcodeId(qrcodeId);
        List<ProcessRecord> processRecordList = processRecordMapper.selectByRecordIds(recordIds);
        return ServerResponse.createBySuccess(processRecordList);
    }

    @Override
    public ServerResponse generateTrace(List<Integer> recordIds) {
        Qrcode qrcode = new Qrcode();
        int resultRow = qrcodeMapper.insert(qrcode);
        if(resultRow == 0){
            return ServerResponse.createByErrorMessage("插入新的二维码记录失败！");
        }
        int qrcodeId = qrcode.getId();
        for (Integer recordId : recordIds) {
            ProcessQrcode processQrcode = new ProcessQrcode(qrcodeId, recordId);
            processQrcodeMapper.insert(processQrcode);
        }
        Map map = Maps.newHashMap();
        map.put("qrcodeId",qrcodeId);
        return ServerResponse.createBySuccess(map);
    }


    public ServerResponse isAdmin(int userId){
        UserRelationship userRelationship = userRelationshipMapper.selectAdminByUserId(userId);
        if (userRelationship == null){
            return ServerResponse.createByErrorMessage("负责人信息查询出错");
        }
        return ServerResponse.createBySuccess(userRelationship);
    }

}
