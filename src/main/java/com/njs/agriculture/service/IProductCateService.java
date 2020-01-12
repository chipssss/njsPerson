package com.njs.agriculture.service;

import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.pojo.ProductionFirstCate;
import com.njs.agriculture.pojo.ProductionSecondCate;
import com.njs.agriculture.pojo.ProductionThirdCate;

/**
 * @author: chips
 * @date: 2020-01-12
 * @description: 产品类别service，产品类别增删改查
 **/
public interface IProductCateService {
    ServerResponse add(String name, int userId);

    ServerResponse getForAndroid(int userId);

    ServerResponse categoryGet(int pageNum, int pageSize);

    ServerResponse firstCateGet();

    ServerResponse secondCateGet();

    ServerResponse thirdCateGet();

    ServerResponse firstCateAdd(ProductionFirstCate firstCate);

    ServerResponse secondCateAdd(ProductionSecondCate secondCate);

    ServerResponse thirdCateAdd(ProductionThirdCate thirdCate);
}
