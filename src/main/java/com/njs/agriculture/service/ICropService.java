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

    ServerResponse cropGetAndroid();

    /**
     * 删除农作物，info / 一类 /二类 0 / 1 /2
     * @param id
     * @param flag
     * @return
     */
    ServerResponse cropDel(int id, int flag);

    ServerResponse cropSecondCateGet();

    ServerResponse cropFirstCateGet();

    ServerResponse cropThirdCateGet();

    /**
     *
     * @param type 1 2 3 分别1 2 3类
     * @param name
     * @param superiorId 上级id
     * @return
     */
    ServerResponse cropCateAdd(int type, String name, int superiorId);
}
