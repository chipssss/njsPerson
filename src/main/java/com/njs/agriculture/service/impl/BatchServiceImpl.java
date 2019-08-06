package com.njs.agriculture.service.impl;

import com.google.common.collect.Lists;
import com.njs.agriculture.VO.BatchInfoVO;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.mapper.CropInfoMapper;
import com.njs.agriculture.mapper.ProductionBatchMapper;
import com.njs.agriculture.pojo.ProductionBatch;
import com.njs.agriculture.service.IBatchService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private CropInfoMapper cropInfoMapper;

    @Autowired
    private ProductionBatchMapper productionBatchMapper;


    @Override
    public ServerResponse batchInfo(int fieldId) {
        List<ProductionBatch> productionBatches = productionBatchMapper.batchInfo(fieldId);
        List<BatchInfoVO> batchInfoVOS = Lists.newLinkedList();
        for (ProductionBatch productionBatch : productionBatches) {
            BatchInfoVO batchInfoVO = new BatchInfoVO();
            BeanUtils.copyProperties(productionBatch, batchInfoVO);
            batchInfoVO.setCropName(cropInfoMapper.selectByPrimaryKey(productionBatch.getCropInfoId()).getName());
            batchInfoVOS.add(batchInfoVO);
        }
        return ServerResponse.createBySuccess(batchInfoVOS);
    }

    @Override
    public ServerResponse batchAdd(ProductionBatch productionBatch) {
        List<ProductionBatch> batchesExisted = productionBatchMapper.selectByFieldId(productionBatch.getFieldId());
        Date start = productionBatch.getPlantTime();
        Date end = productionBatch.getCollectTime();
        if(productionBatch.getPlantTime().after(productionBatch.getCollectTime())){
            return ServerResponse.createByErrorMessage("时间不规范，种植时间比采割时间早!");
        }
        for (ProductionBatch batch : batchesExisted) {
            if(!((start.after(batch.getCollectTime()) ||start.equals(batch.getCollectTime()))
                    ||
                    (end.before(batch.getPlantTime()) || end.equals(batch.getPlantTime())))){
                return ServerResponse.createByErrorMessage("时间冲突,请重新输入数据!");
            }
        }
        int resultRow = productionBatchMapper.insert(productionBatch);
        if(resultRow == 0){
            return ServerResponse.createByErrorMessage("插入记录失败!");
        }
        return ServerResponse.createBySuccess(productionBatch.getId());
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
