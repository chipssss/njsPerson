package com.njs.agriculture.service;

import com.alibaba.fastjson.JSONObject;
import com.njs.agriculture.common.ServerResponse;

import java.util.Map;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/10/8
 * @Description:
 */
public interface IActivationService {

    /**
     * 查看激活状态
     * @param code
     * @return
     */
    JSONObject checkActivation(String code);

    /**
     * 绑定激活产品
     * @param code
     * @param batchId
     * @param userId
     * @return
     */
    ServerResponse bindProduct(String code, String batchId, int userId,String productName);


    /**
     * 获取单个用户激活记录
     * @return
     */
    ServerResponse getActivationStream(int userId);

    /**
     * 扫描二维码获取记录，包括企业信息，加工记录，种植记录
     * @param code
     * @return
     */
    ServerResponse scanGetRecords(String code);

    /**
     * 扫描二维码获取记录，包括企业信息，田块信息，种植记录
     * @param fieldId
     * @return
     */
    ServerResponse scanGetFieldRecords(int fieldId);

    void addUserInfo(Map map, int sourceId, int source);
}
