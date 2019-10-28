package com.njs.agriculture.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.njs.agriculture.VO.MachineVO;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.mapper.*;
import com.njs.agriculture.pojo.*;
import com.njs.agriculture.service.IActivationService;
import com.njs.agriculture.service.IProcessRecordService;
import com.njs.agriculture.service.IProductService;
import com.njs.agriculture.service.IUserService;
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

    @Autowired
    UserMapper userMapper;

    @Autowired
    EnterpriseMapper enterpriseMapper;

    @Autowired
    MachiningMapper machiningMapper;

    @Autowired
    IProductService iProductService;

    @Autowired
    IProcessRecordService iProcessRecordService;

    @Override
    public JSONObject checkActivation(String code) {
        int resultRow = productActivationMapper.checkByCode(code);
        int status = resultRow == 1 ? 1 : 0;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", status);
        return jsonObject;
    }

    @Override
    public ServerResponse bindProduct(String code, String batchId, int userId, String productName) {
//        ProductStock productStock = productStockMapper.selectByBatchId(batchId);
//        if(productStock == null){
//            return ServerResponse.createByErrorMessage("批次不存在");
//        }
//        ProductionBatch productionBatch = productionBatchMapper.selectByBarcode(productStock.getBarcode());
//        if(productionBatch == null){
//            return ServerResponse.createByErrorMessage("批次不存在");
//        }
//        Field field = fieldMapper.selectByPrimaryKey(productionBatch.getFieldId());
        ProductActivation productActivation = new ProductActivation();
//        if(field != null){
//            productActivation.setProductName(field.getCropName());
//        }
        productActivation.setProductName(productName);
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

    @Override
    public ServerResponse scanGetRecords(String code) {
        ProductActivation productActivation = productActivationMapper.getByCode(code);
        if(code == null){
            return ServerResponse.createByErrorMessage("没有该条二维码记录!");
        }
        ProductStock productStock = productStockMapper.selectByBatchId(productActivation.getBatchId());
        if(productStock == null){
            return ServerResponse.createByErrorMessage("库存表没有该记录！");
        }
        Map result = Maps.newHashMap();
        if(productStock.getSource() == 0){
            User user = userMapper.selectByPrimaryKey(productStock.getSourceId());
            if(user != null){
                result.put("companyTitle", user.getUsername());
            }
        }else{
            Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(productStock.getSourceId());
            if(enterprise != null){
                result.put("companyTitle", enterprise.getName());
            }
        }
        List<Machining> machiningList = machiningMapper.selectByStockId(productStock.getId());
        List<MachineVO> machineVOList = iProductService.machine2MachineVO(machiningList);
        result.put("machineRecord", machineVOList);
        ProductionBatch productionBatch = productionBatchMapper.selectByBarcode(productStock.getBarcode());
        if(productionBatch == null){
            return ServerResponse.createByErrorMessage("批次为空！");
        }
        ServerResponse<List> listServerResponse = iProcessRecordService.trace(0,0,null,null,productionBatch.getId());
        result.put("processRecord", listServerResponse.getData());
        return ServerResponse.createBySuccess(result);
    }
}
