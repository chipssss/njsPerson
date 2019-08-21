package com.njs.agriculture.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.njs.agriculture.VO.*;
import com.njs.agriculture.common.Const;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.mapper.*;
import com.njs.agriculture.pojo.*;
import com.njs.agriculture.service.IFileService;
import com.njs.agriculture.service.IProcessRecordService;
import com.njs.agriculture.service.IUserService;
import com.njs.agriculture.utils.DateUtil;
import com.njs.agriculture.utils.MathUtil;
import com.njs.agriculture.utils.PropertiesUtil;
import com.sun.scenario.effect.Crop;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;


/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/28
 * @Description:
 */
@Service("iProcessRecordService")
@Slf4j
public class ProcessRecordServiceImpl implements IProcessRecordService {

    @Autowired
    private InputUsedMapper inputUsedMapper;

    @Autowired
    private InputUserMapper inputUserMapper;

    @Autowired
    private InputEnterpriseMapper inputEnterpriseMapper;


    @Autowired
    private IFileService iFileService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private FieldMapper fieldMapper;

    @Autowired
    private ProductionBatchMapper productionBatchMapper;

    @Autowired
    private ProcessRecordMapper processRecordMapper;

    @Autowired
    private ProcessImageMapper processImageMapper;


    @Autowired
    private ProcessQrcodeMapper processQrcodeMapper;

    @Autowired
    private CropInfoMapper cropInfoMapper;

    @Autowired
    private OperationMapper operationMapper;

    @Autowired
    private UserRelationshipMapper userRelationshipMapper;

    @Autowired
    private RecoveryRecordMapper recoveryRecordMapper;

    @Override
    public ServerResponse processRecord(int userId, String startTime, String endTime, int fieldId, int cropId, int pageNum, int pageSize){
        Date sTime = DateUtil.strToDate(startTime, DateUtil.SHORT_FORMAT);
        Date eTime = DateUtil.strToDate(endTime, DateUtil.SHORT_FORMAT);
        ServerResponse<Map> serverResponse = iUserService.isManager(userId);
        List<ProcessRecord> processRecordList;
        PageHelper.startPage(pageNum, pageSize);
        PageHelper.orderBy("source_id, create_time desc");
        processRecordList = processRecordMapper.selectByCondition(sTime, eTime, fieldId, cropId, (int)serverResponse.getData().get("sourceId"), (int)serverResponse.getData().get("source"));
        PageInfo pageInfo = new PageInfo(processRecordList);
        pageInfo.setList(records2recordVO(processRecordList));
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse getRecordCrop(int userId) {
        ServerResponse<Map> serverResponse = iUserService.isManager(userId);
        List<Integer> cropIdList = processRecordMapper
                .selectCropIdBySource((int)serverResponse.getData().get("sourceId"), (int)serverResponse.getData().get("source"));

        List<SimpleVO> simpleVOS = Lists.newLinkedList();
        for (Integer integer : cropIdList) {
            CropInfo cropInfo =  cropInfoMapper.selectByPrimaryKey(integer);
            if(cropInfo == null){
                continue;
            }
            SimpleVO simpleVO = new SimpleVO(integer, cropInfo.getName());
            simpleVOS.add(simpleVO);
        }
        return ServerResponse.createBySuccess(simpleVOS);
    }

    @Override
    public ServerResponse trace(int pageNum, int pageSize, Date startTime, Date endTime, int batchId) {
        //1.先查List<int> 生产记录的id
        List<Integer> recordIds = processQrcodeMapper.selectByBatchId(batchId);
        PageHelper.startPage(pageNum, pageSize);
        List<ProcessRecord> processRecordList = processRecordMapper.selectByRecordIds(startTime, endTime, recordIds);

        return ServerResponse.createBySuccess(records2recordVO(processRecordList));
    }

    @Override
    public ServerResponse generateTrace(int batchId, List<Integer> recordIds) {
        for (Integer recordId : recordIds) {
            ProcessQrcode processQrcode = new ProcessQrcode(batchId, recordId);
            processQrcodeMapper.insert(processQrcode);
        }
        return ServerResponse.createBySuccess();
    }

    @Override
    @Transactional //设置事务回滚，保证原子操作
    public ServerResponse addProcess(ProcessRecordInfoVO processRecordInfoVO, String userName) {

        //有依赖，如果可以建议重写接口
        Field field = fieldMapper.selectByPrimaryKey(processRecordInfoVO.getFieldId());
        CropInfo crop = cropInfoMapper.selectByPrimaryKey(field.getCropId());
        if(field == null || crop == null){
            return ServerResponse.createByErrorMessage("找不到田块或者农作物");
        }
        for (String s : processRecordInfoVO.getOperationList()) {
            if(s.contains(Const.RECOVERY)){
                //插入到记录表
                RecoveryRecord recoveryRecord = new RecoveryRecord();
                recoveryRecord.setCrop(crop.getName());
                recoveryRecord.setFieldId(field.getId());
                recoveryRecord.setSource(field.getSource());
                recoveryRecord.setSourceId(field.getSourceId());
                recoveryRecord.setUserName(userName);
                recoveryRecord.setStatus(0);
                ServerResponse serverResponse = insertRecoveryRecord(recoveryRecord);
                if(!serverResponse.isSuccess()){
                    return serverResponse;
                }
            }
        }

        ProcessRecord processRecord = new ProcessRecord();
//        processRecord.setBatchId(productionBatch.getId());
        processRecord.setFieldId(processRecordInfoVO.getFieldId());
        processRecord.setCropId(field.getCropId());
        processRecord.setLocation(processRecordInfoVO.getLocation());
        processRecord.setWeather(processRecordInfoVO.getWeather());
        processRecord.setRemark(processRecordInfoVO.getRemark());
        processRecord.setSource(field.getSource());
        processRecord.setSourceId(field.getSourceId());
        String operations = Joiner.on(",").join(processRecordInfoVO.getOperationList());
        processRecord.setOperation(operations);
        StringBuilder s = new StringBuilder();
        for (ProcessRecordInfoVO.Input input : processRecordInfoVO.getInputList()) {
            if(input.getSource() == 0){
                //先进行记录，删减，然后进行拼接
                InputUser inputUser = inputUserMapper.selectByPrimaryKey(input.getInputId());
                if(inputUser == null){
                    return ServerResponse.createByErrorMessage("找不到投入品");
                }
                double result = MathUtil.sub(inputUser.getQuantity().toString(), String.valueOf(input.getQuantity()));
                if(result < 0){
                    return ServerResponse.createByErrorMessage("数量超过存在额!");
                }
                inputUser.setQuantity((float)result);
                inputUserMapper.updateByPrimaryKeySelective(inputUser);
                InputUsed inputUsed = new InputUsed(0, inputUser.getId(), input.getQuantity());
                inputUsedMapper.insert(inputUsed);
                s.append(inputUser.getName() + ",");
            }else {
                InputEnterprise inputEnterprise = inputEnterpriseMapper.selectByPrimaryKey(input.getInputId());
                if(inputEnterprise == null){
                    return ServerResponse.createByErrorMessage("找不到投入品");
                }
                double result = MathUtil.sub(inputEnterprise.getQuantity().toString(), String.valueOf(input.getQuantity()));
                if(result < 0){
                    return ServerResponse.createByErrorMessage("数量超过存在额!");
                }
                inputEnterprise.setQuantity((float)result);
                inputEnterpriseMapper.updateByPrimaryKeySelective(inputEnterprise);
                InputUsed inputUsed = new InputUsed(1, inputEnterprise.getId(), input.getQuantity());
                inputUsedMapper.insert(inputUsed);
                s.append(inputEnterprise.getName() + ",");
            }
        }
        String inputRecord = s.toString();
        inputRecord = inputRecord.substring(0, inputRecord.length()-1);
        processRecord.setInputRecord(inputRecord);
        //1.先上传记录
        int resultRow = processRecordMapper.insert(processRecord);
        if(resultRow == 0){
            return ServerResponse.createByErrorMessage("上传记录失败！");
        }
        //2.批量插入
        for (String image : processRecordInfoVO.getImages()) {
            processImageMapper.insert(new ProcessImage(processRecord.getId(), image));
        }
        return ServerResponse.createBySuccess(processRecord);
    }

    @Override
    @Transactional
    public ServerResponse processImgUpload(List<MultipartFile> files) {
        String path = PropertiesUtil.getProperty("uploadDir") + "process";
        List<String> images = Lists.newLinkedList();
        for (MultipartFile file : files) {
            String fileName = iFileService.upload(file, path);
            if(fileName == null){
                log.error("上传{}失败，事务回滚", file.getOriginalFilename());
                throw new RuntimeException("上传"+file.getOriginalFilename()+"失败,事务回滚！");
            }
            images.add(Const.PROCESSIMGPREFIX + fileName);
        }
        return ServerResponse.createBySuccess(images);
    }

    @Override
    public ServerResponse getOperation() {
        List<String> operations = operationMapper.selectAll();
        if(operations.isEmpty()){
            return ServerResponse.createByErrorMessage("未知错误！");
        }
        return ServerResponse.createBySuccess(operations);
    }

    @Override
    public ServerResponse getFieldsByBatchExist(int userId) {
        List<UserRelationship> userRelationships = userRelationshipMapper.selectByUserId(userId);

        List<FieldListVO> fieldListExisted = Lists.newLinkedList();
        List<FieldListVO> fieldListNotExisted = Lists.newLinkedList();
        for (UserRelationship relationship : userRelationships) {
            List<FieldListVO> enterpriseExisted = fieldMapper.selectExistedByUserId(1, relationship.getEnterpriseId());
            List<FieldListVO> enterpriseNotExisted = fieldMapper.selectAllBySourceId(1, relationship.getEnterpriseId());
            enterpriseNotExisted.removeAll(enterpriseExisted);
            fieldListNotExisted.addAll(enterpriseNotExisted);
            fieldListExisted.addAll(enterpriseExisted);
        }
        List<FieldListVO> userExisted = fieldMapper.selectExistedByUserId(0, userId);
        List<FieldListVO> userNotExisted = fieldMapper.selectAllBySourceId(0, userId);
        userNotExisted.removeAll(userExisted);
        fieldListNotExisted.addAll(userNotExisted);
        fieldListExisted.addAll(userExisted);
        for (FieldListVO fieldListVO : fieldListExisted) {
            fieldListVO.setHasBatch(true);
        }
        for (FieldListVO fieldListVO : fieldListNotExisted) {
            fieldListVO.setHasBatch(false);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fieldListExisted", fieldListExisted);
        jsonObject.put("fieldListNotExisted", fieldListNotExisted);
        return ServerResponse.createBySuccess(jsonObject);
    }

    @Override
    public ServerResponse getBatchesByUserId(int userId) {
        return ServerResponse.createBySuccess(productionBatchMapper.selectByExistProcessRecord(userId));
    }

    @Override
    public ServerResponse getRecoveryRecord(int source, int sourceId) {
        List<RecoveryRecord> recoveryRecordList = recoveryRecordMapper.selectBySource(source, sourceId);
        List<RecoveryRecordVO> recoveryRecordVOList = Lists.newLinkedList();
        for (RecoveryRecord recoveryRecord : recoveryRecordList) {
            RecoveryRecordVO recoveryRecordVO = new RecoveryRecordVO();
            BeanUtils.copyProperties(recoveryRecord, recoveryRecordVO);
            Field field = fieldMapper.selectByPrimaryKey(recoveryRecord.getFieldId());
            recoveryRecordVO.setFieldName(field.getName());
            recoveryRecordVOList.add(recoveryRecordVO);
        }
        List<RecoveryRecord> created = Lists.newLinkedList();
        List<RecoveryRecord> uncreated = Lists.newLinkedList();
        for (RecoveryRecordVO recoveryRecordVO : recoveryRecordVOList) {
            if(recoveryRecordVO.getStatus() == 0){
                uncreated.add(recoveryRecordVO);
            }else {
                created.add(recoveryRecordVO);
            }
        }
        Map map = Maps.newHashMap();
        map.put("created", created);
        map.put("uncreated", uncreated);
        return ServerResponse.createBySuccess(map);
    }



    public List<ProcessRecordVO> records2recordVO(List<ProcessRecord> processRecordList){
        List<ProcessRecordVO> processRecords = Lists.newArrayList();
        if(!processRecordList.isEmpty()){
            for (ProcessRecord processRecord : processRecordList) {
                ProcessRecordVO processRecordVO = new ProcessRecordVO();
                BeanUtils.copyProperties(processRecord, processRecordVO);
                List<String> images = processImageMapper.selectByRecordId(processRecord.getId());
                if(!images.isEmpty()){
                    processRecordVO.setImages(images);
                }
                Field field = fieldMapper.selectByPrimaryKey(processRecord.getFieldId());
                if(field != null){
                    processRecordVO.setFieldName(field.getName());
                }
                CropInfo cropInfo = cropInfoMapper.selectByPrimaryKey(processRecord.getCropId());
                if(cropInfo != null){
                    processRecordVO.setCropName(cropInfo.getName());
                }
                processRecords.add(processRecordVO);
            }
        }
        return processRecords;
    }

    public ServerResponse insertRecoveryRecord(RecoveryRecord recoveryRecord){
        int resultRow = recoveryRecordMapper.insert(recoveryRecord);
        if(resultRow == 0){
            return ServerResponse.createByErrorMessage("插入失败！");
        }
        return ServerResponse.createBySuccess(recoveryRecord);
    }

}

