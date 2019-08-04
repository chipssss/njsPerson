package com.njs.agriculture.service;

import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.pojo.ServicePool;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/8/3
 * @Description:
 */
public interface IServiceService {

    ServerResponse serviceApply(ServicePool servicePool);

    ServerResponse serviceListGet();

    ServerResponse serviceInfoGet(int flag, int sourceId);

    ServerResponse serviceApplyRecord(int userId);

    //backend

    ServerResponse applyerGet(int status);

    ServerResponse serviceUpdate(int id, int status, String reply);

}
