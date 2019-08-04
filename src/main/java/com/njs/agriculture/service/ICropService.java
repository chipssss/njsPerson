package com.njs.agriculture.service;

import com.njs.agriculture.common.ServerResponse;


/**
 * @Auther: SaikeiLEe
 * @Date: 2019/8/1
 * @Description:
 */
public interface ICropService {
    ServerResponse cropAdd(int userId, String name, int typeId);

    ServerResponse cropGet(int pageNum, int pageSize);
}
