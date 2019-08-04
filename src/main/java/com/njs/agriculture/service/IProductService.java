package com.njs.agriculture.service;

import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.pojo.ProductPool;
import com.njs.agriculture.pojo.ProductionFirstCate;
import com.njs.agriculture.pojo.ProductionSecondCate;
import com.njs.agriculture.pojo.ProductionThirdCate;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/8/2
 * @Description:
 */
public interface IProductService {

    ServerResponse categoryGet(int pageNum, int pageSize);

    ServerResponse productAdd(ProductPool productPool);

    ServerResponse firstCateAdd(ProductionFirstCate firstCate);

    ServerResponse secondCateAdd(ProductionSecondCate secondCate);

    ServerResponse thirdCateAdd(ProductionThirdCate thirdCate);

    /**
     * 类别删除
     * @param id
     * @param flag 1为一类，2为二类，3为三类
     * @return
     */
    ServerResponse cateDel(int id, int flag);
}
