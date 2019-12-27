package com.njs.agriculture.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.njs.agriculture.VO.FieldInfoVO;
import com.njs.agriculture.VO.MachineVO;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.mapper.*;
import com.njs.agriculture.pojo.*;
import com.njs.agriculture.service.*;
import com.njs.agriculture.utils.DateUtil;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    RootRecordDOMapper rootRecordDOMapper;

    @Autowired
    IFieldService iFieldService;

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

    /**
     * 溯源获取记录
     *
     * @param code 这里code兼容两种形式，一，激活码，根据激活码找到绑定的批次码；
     *             二，种植批次码，用于用户自身的批次查询
     * @return
     */
    @Override
    public ServerResponse scanGetRecords(String code) {
        if (code == null) return ServerResponse.createByErrorMessage("没有该条二维码记录!");
        // 第一种情况，激活码找到批次码
        ProductActivation productActivation = productActivationMapper.getByCode(code);

        ProductStock productStock;
        // 第二种情况，为种植的批次码
        productStock = productStockMapper.selectByBatchId(productActivation == null ? code:productActivation.getBatchId());

        if (productStock == null) {
            return ServerResponse.createByErrorMessage("库存表没有该记录！");
        }
        ProductBasic productBasic = productBasicMapper.selectByPrimaryKey(productStock.getProductId());

        Map result = Maps.newHashMap();

        result.put("productBasic", productBasic);
        addUserInfo(result, productStock.getSourceId(), productStock.getSource());

        RootRecordDO rootRecordDO = rootRecordDOMapper.selectByBatchId(productStock.getBarcode());
        List<Machining> machiningList = machiningMapper.selectByKey(rootRecordDO.getMachineId(), rootRecordDO.getPackId());
        List<MachineVO> machineVOList = iProductService.machine2MachineVO(machiningList);
        result.put("machineRecord", machineVOList);

        ServerResponse<List> listServerResponse = iProcessRecordService.trace(0, 0, rootRecordDO.getFieldId(), rootRecordDO.getPlantStart(), rootRecordDO.getPlantEnd());
        result.put("processRecord", listServerResponse.getData());
        return ServerResponse.createBySuccess(result);
    }

    @Override
    public ServerResponse scanGetFieldRecords(int fieldId) {
        FieldInfoVO fieldInfoVO = iFieldService.selectFieldInfoByFieldId(fieldId);
        if (fieldInfoVO == null) {
            return ServerResponse.createByErrorMessage("田块不存在");
        }
        // 添加田块信息
        Map result = Maps.newHashMap();
        result.put("field", fieldInfoVO);

        // 添加用户信息
        addUserInfo(result, fieldInfoVO.getSourceId(), fieldInfoVO.getSource());

        // 添加种植记录
        ServerResponse<List> listServerResponse = iProcessRecordService.trace(0, 0, fieldId, null, null);
        result.put("processRecord", listServerResponse.getData());

        return ServerResponse.createBySuccess(result);
    }


    @Override
    public void addUserInfo(Map map, int sourceId, int source) {
        if (source == 0) {
            User user = userMapper.selectByPrimaryKey(sourceId);
            if (user != null) {
                map.put("companyTitle", user.getUsername());
                map.put("username", user.getUsername());
                map.put("location", user.getLocation() == null ? " " : user.getLocation());
            }
        } else {
            Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(sourceId);
            if (enterprise != null) {
                map.put("companyTitle", enterprise.getName());
                map.put("location", enterprise.getAddress());
            }
        }
    }
}
