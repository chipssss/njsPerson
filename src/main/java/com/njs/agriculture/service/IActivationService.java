package com.njs.agriculture.service;

import com.alibaba.fastjson.JSONObject;
import com.njs.agriculture.common.ServerResponse;

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
    ServerResponse bindProduct(String code, int batchId, int userId);


    /**
     * 获取单个用户激活记录
     * @return
     */
    ServerResponse getActivationStream(int userId);
}
