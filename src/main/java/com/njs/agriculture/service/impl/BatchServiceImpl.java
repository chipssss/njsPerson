package com.njs.agriculture.service.impl;

import com.njs.agriculture.VO.BatchInfoVO;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.mapper.ProductionBatchMapper;
import com.njs.agriculture.mapper.RecoveryRecordMapper;
import com.njs.agriculture.pojo.ProductionBatch;
import com.njs.agriculture.pojo.RecoveryRecord;
import com.njs.agriculture.service.IBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/30
 * @Description:
 */
@Service("iBatchService")
public class BatchServiceImpl implements IBatchService {



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
    public ServerResponse batchAdd(BatchInfoVO batchInfoVO) {
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
        batchInfoVO.setPlantTime(start);
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
}
