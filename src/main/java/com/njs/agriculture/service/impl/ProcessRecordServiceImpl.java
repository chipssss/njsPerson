package com.njs.agriculture.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.njs.agriculture.VO.FieldVO;
import com.njs.agriculture.VO.ProcessRecordInfoVO;
import com.njs.agriculture.VO.ProcessRecordVO;
import com.njs.agriculture.common.Const;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.mapper.*;
import com.njs.agriculture.pojo.*;
import com.njs.agriculture.service.IFileService;
import com.njs.agriculture.service.IProcessRecordService;
import com.njs.agriculture.utils.DateUtil;
import com.njs.agriculture.utils.MathUtil;
import com.njs.agriculture.utils.PropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.njs.agriculture.utils.MathUtil.sub;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/28
 * @Description:
 */
@Service("iIFieldService")
@Slf4j
public class ProcessRecordServiceImpl implements IProcessRecordService {

    @Autowired
    private InputUsedMapper inputUsedMapper;

    @Autowired
    private InputUserMapper inputUserMapper;

    @Autowired
    private InputEnterpriseMapper inputEnterpriseMapper;

    @Autowired
    private InputReturnMapper inputReturnMapper;

    @Autowired
    private IFileService iFileService;

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
        List<ProcessRecord> processRecordList = Lists.newArrayList();
        PageHelper.startPage(pageNum, pageSize);
        PageHelper.orderBy("source_id, create_time desc");
        if(serverResponse.isSuccess()){
            processRecordList = processRecordMapper.selectByCondition(sTime, eTime, batchId, cropId, serverResponse.getData().getEnterpriseId());
        }else {
            processRecordList = processRecordMapper.selectByCondition(sTime, eTime, batchId, cropId, userId);
        }

        return ServerResponse.createBySuccess(records2recordVO(processRecordList));
    }

    @Override
    public ServerResponse trace(int qrcodeId) {
        //1.先查List<int> 生产记录的id
        List<Integer> recordIds = processQrcodeMapper.selectByQrcodeId(qrcodeId);
        List<ProcessRecord> processRecordList = processRecordMapper.selectByRecordIds(recordIds);

        return ServerResponse.createBySuccess(records2recordVO(processRecordList));
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

    @Override
    @Transactional //设置事务回滚，保证原子操作
    public ServerResponse addProcess(ProcessRecordInfoVO processRecordInfoVO) {

        ProductionBatch productionBatch = productionBatchMapper.onlyBatch(processRecordInfoVO.getFieldId(), new Date());
        if(productionBatch == null){
            return ServerResponse.createByErrorMessage("找不到批次记录！");
        }
        Field field = fieldMapper.selectByPrimaryKey(processRecordInfoVO.getFieldId());
        ProcessRecord processRecord = new ProcessRecord();
        processRecord.setBatchId(productionBatch.getId());
        processRecord.setCropId(productionBatch.getCropInfoId());
        processRecord.setLocation(processRecordInfoVO.getLocation());
        processRecord.setWeather(processRecordInfoVO.getWeather());
        processRecord.setRemark(processRecordInfoVO.getRemark());
        processRecord.setSource(field.getSource());
        processRecord.setSourceid(field.getSourceId());
        String operations = Joiner.on(",").join(processRecordInfoVO.getOperationList());
        processRecord.setOperation(operations);
        StringBuilder s = new StringBuilder();
        for (ProcessRecordInfoVO.Input input : processRecordInfoVO.getInputList()) {
            if(input.getSource() == 0){
                //先进行记录，删减，然后进行拼接
                InputUser inputUser = inputUserMapper.selectByPrimaryKey(input.getInputId());
                double result = MathUtil.sub(inputUser.getQuantity(), input.getQuantity());
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
                double result = MathUtil.sub(inputEnterprise.getQuantity(), input.getQuantity());
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
        return ServerResponse.createBySuccess(processRecord.getId());
    }

    @Override
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
    public ServerResponse returnInput(int id, float quantity) {
        //1.利用id取回记录，判断数量是否超出
        InputUser inputUser = inputUserMapper.selectByPrimaryKey(id);
        if(inputUser == null){
            return ServerResponse.createByErrorMessage("id无效，记录为空");
        }
        if(inputUser.getSource() == 0){
            return ServerResponse.createByErrorMessage("该投入品不是由企业领用！");
        }
        double result = MathUtil.sub(inputUser.getQuantity(), quantity);
        if(result < 0){
            return ServerResponse.createByErrorMessage("数量超过存在额!");
        }
        //2.对数量进行删减，存入数据库
        inputUser.setQuantity((float) result);
        inputUserMapper.updateByPrimaryKeySelective(inputUser);
        InputEnterprise inputEnterprise = inputEnterpriseMapper.selectByPrimaryKey(inputUser.getSourceId());
        float resultE = (float)MathUtil.add(inputEnterprise.getQuantity(), quantity);
        inputEnterprise.setQuantity(resultE);
        inputEnterpriseMapper.updateByPrimaryKeySelective(inputEnterprise);
        //3.更新退回表
        InputReturn inputReturn = new InputReturn(inputUser.getUserId(), inputEnterprise.getId(), quantity);
        inputReturnMapper.insert(inputReturn);
        return ServerResponse.createBySuccess();
    }


    public ServerResponse isAdmin(int userId){
        UserRelationship userRelationship = userRelationshipMapper.selectAdminByUserId(userId);
        if (userRelationship == null){
            return ServerResponse.createByErrorMessage("负责人信息查询出错");
        }
        return ServerResponse.createBySuccess(userRelationship);
    }

    public List<ProcessRecordVO> records2recordVO(List<ProcessRecord> processRecordList){
        List<ProcessRecordVO> processRecords = Lists.newArrayList();
        if(!processRecordList.isEmpty()){
            for (ProcessRecord processRecord : processRecordList) {
                ProcessRecordVO processRecordVO = new ProcessRecordVO();
                BeanUtils.copyProperties(processRecord, processRecordVO);
                List<String> images = processImageMapper.selectByRecordId(processRecord.getId());
                processRecordVO.setImages(images);
                ProductionBatch productionBatch = productionBatchMapper.selectByPrimaryKey(processRecord.getBatchId());
                processRecordVO.setBatchName(productionBatch.getName());
                processRecordVO.setFieldName(fieldMapper.selectByPrimaryKey(productionBatch.getFieldId()).getName());
                processRecords.add(processRecordVO);
            }
        }
        return processRecords;
    }


}
