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

    /**
     * 删除农作物，info / 一类 /二类 0 / 1 /2
     * @param id
     * @param flag
     * @return
     */
    ServerResponse cropDel(int id, int flag);
}
