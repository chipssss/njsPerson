package com.njs.agriculture.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.mapper.*;
import com.njs.agriculture.pojo.*;
import com.njs.agriculture.service.IActivationService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/10/8
 * @Description:
 */
@Service("iActivationService")
public class ActivationServiceImpl implements IActivationService {

    @Autowired
    ProductStockMapper productStockMapper;

    @Autowired
    ProductBasicMapper productBasicMapper;

    @Autowired
    private ProductionBatchMapper productionBatchMapper;

    @Autowired
    private FieldMapper fieldMapper;

    @Autowired
    private ProductActivationMapper productActivationMapper;

    @Override
    public JSONObject checkActivation(String code) {
        int resultRow = productActivationMapper.checkByCode(code);
        int status = resultRow == 1 ? 1 : 0;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", status);
        return jsonObject;
    }

    @Override
    public ServerResponse bindProduct(String code, String batchId, int userId) {
        ProductStock productStock = productStockMapper.selectByBatchId(batchId);
        if(productStock == null){
            return ServerResponse.createByErrorMessage("批次不存在");
        }
        ProductionBatch productionBatch = productionBatchMapper.selectByBarcode(productStock.getBarcode());
        if(productionBatch == null){
            return ServerResponse.createByErrorMessage("批次不存在");
        }
        Field field = fieldMapper.selectByPrimaryKey(productionBatch.getFieldId());
        if(field == null){
            return ServerResponse.createByErrorMessage("批次的田块不存在");
        }
        ProductActivation productActivation = new ProductActivation();
        productActivation.setProductName(field.getCropName());
        productActivation.setCode(code);
        productActivation.setBatchId(batchId);
        productActivation.setUserId(userId);
        int resultRow = productActivationMapper.insert(productActivation);
        return ServerResponse.createByResultRow(resultRow);
    }


    @Override
    public ServerResponse getActivationStream(int userId) {
        List<ProductActivation> productActivations = productActivationMapper.getByUserId(userId);
        return ServerResponse.createBySuccess(productActivations);
    }
}
