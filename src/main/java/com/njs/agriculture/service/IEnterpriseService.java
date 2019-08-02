package com.njs.agriculture.service;

import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.pojo.Enterprise;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/8/2
 * @Description:
 */
public interface IEnterpriseService {

    ServerResponse enterpriseAdd(Enterprise enterprise);

    ServerResponse enterpriseGet(int status);

    ServerResponse personnelGet(int enterpriseId);

}
