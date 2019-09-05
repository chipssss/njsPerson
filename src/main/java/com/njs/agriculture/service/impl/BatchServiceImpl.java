package com.njs.agriculture.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.njs.agriculture.VO.BatchInfoVO;
import com.njs.agriculture.VO.FieldListVO;
import com.njs.agriculture.VO.FieldVO;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.mapper.FieldMapper;
import com.njs.agriculture.mapper.ProductionBatchMapper;
import com.njs.agriculture.mapper.RecoveryRecordMapper;
import com.njs.agriculture.pojo.Field;
import com.njs.agriculture.pojo.ProductionBatch;
import com.njs.agriculture.pojo.RecoveryRecord;
import com.njs.agriculture.service.IBatchService;
import com.njs.agriculture.service.IUserService;
import com.njs.agriculture.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileDescriptor;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/30
 * @Description:
 */
@Service("iBatchService")
public class BatchServiceImpl implements IBatchService {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private FieldMapper fieldMapper;

    @Autowired
    private ProductionBatchMapper productionBatchMapper;

    @Autowired
    private RecoveryRecordMapper recoveryRecordMapper;



    @Override
    public ServerResponse batchInfoByFinished(int fieldId, int finished) {
        return ServerResponse.createBySuccess(productionBatchMapper.batchInfoByFinished(fieldId, finished));
    }

    @Override
    public ServerResponse batchInfoByGenerated(int fieldId, int generated) {
        return ServerResponse.createBySuccess(productionBatchMapper.batchInfoByGenerated(fieldId, generated));
    }

    @Override
    @Transactional
    public ServerResponse batchAdd(BatchInfoVO batchInfoVO, int userId) {
        List<ProductionBatch> batchesExisted = productionBatchMapper.selectByFieldId(batchInfoVO.getFieldId());
        Date start = batchInfoVO.getPlantTime();
        Date end = batchInfoVO.getCollectTime();
        if(batchInfoVO.getPlantTime().after(batchInfoVO.getCollectTime())){
            return ServerResponse.createByErrorMessage("时间不规范，种植时间比采割时间早!");
        }
        for (ProductionBatch batch : batchesExisted) {
            /*if(!((start.after(batch.getCollectTime()) ||start.equals(batch.getCollectTime()))
                    ||
                    (end.before(batch.getPlantTime()) || end.equals(batch.getPlantTime())))){
                return ServerResponse.createByErrorMessage("时间冲突,请重新输入数据!");
            }*/
            if(end.before(batch.getCollectTime())){
                return ServerResponse.createByErrorMessage("时间冲突，收割时间早于上一批收割时间！");
            }
            if(start.before(batch.getCollectTime())){
                start = batch.getCollectTime();
            }
        }
        Field field = fieldMapper.selectByPrimaryKey(batchInfoVO.getFieldId());
        Map map = iUserService.isManager(userId).getData();
        batchInfoVO.setName(batchNameGenerate(end, (int)map.get("source"), (int)map.get("sourceId"), batchInfoVO.getFieldId(), field.getCropId()));
        batchInfoVO.setPlantTime(start);
        batchInfoVO.setGenerated(0);
        int recoveryId = batchInfoVO.getRecoveryRecordId();
        RecoveryRecord recoveryRecord = new RecoveryRecord();
        recoveryRecord.setId(recoveryId);
        recoveryRecord.setStatus(1);
        int resultCount = recoveryRecordMapper.updateByPrimaryKeySelective(recoveryRecord);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("更新记录失败!");
        }
        int resultRow = productionBatchMapper.insert(batchInfoVO);
        if(resultRow == 0){
            return ServerResponse.createByErrorMessage("插入记录失败!");
        }
        return ServerResponse.createBySuccess(batchInfoVO.getId());
    }

    @Override
    public ServerResponse batchDel(int id) {
        int resultRow = productionBatchMapper.deleteByPrimaryKey(id);
        if(resultRow == 0){
            return ServerResponse.createByErrorMessage("删除记录失败!");
        }
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse getBatchesFinishedOrGenerated(int userId, int flag) {
        Map map = iUserService.isManager(userId).getData();
        List<FieldListVO> fieldVOList = fieldMapper.selectAllBySourceId((int)map.get("source"), (int)map.get("sourceId"));
        List<ProductionBatch> productionBatchList = productionBatchMapper.selectByFieldList(fieldVOList);
        Map result = Maps.newHashMap();
        if (flag == 0) {
            List<ProductionBatch> finished = Lists.newLinkedList();
            List<ProductionBatch> unfinished = Lists.newLinkedList();
            for (ProductionBatch productionBatch : productionBatchList) {
                if(productionBatch.getFinish() == 1){
                    finished.add(productionBatch);
                }else{
                    unfinished.add(productionBatch);
                }
            }
            result.put("finished", finished);
            result.put("unfinished", unfinished);
        }else{
            List<ProductionBatch> generated = Lists.newLinkedList();
            List<ProductionBatch> unGenerated = Lists.newLinkedList();
            for (ProductionBatch productionBatch : productionBatchList) {
                if(productionBatch.getGenerated() == 1){
                    generated.add(productionBatch);
                }else{
                    unGenerated.add(productionBatch);
                }
            }
            result.put("generated", generated);
            result.put("unGenerated", unGenerated);
        }

        return ServerResponse.createBySuccess(result);
    }

    public String batchNameGenerate(Date collectTime, int source, int sourceId, int fieldId, int cropId){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(DateUtil.dateToStr(collectTime, "yyyyMMdd")).append(source).append(sourceId)
                .append(fieldId).append(cropId);
        return stringBuffer.toString();
    }
}
