package com.njs.agriculture.service;

import com.njs.agriculture.VO.MachineVO;
import com.njs.agriculture.VO.ProductSecondCateVO;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.pojo.*;

import java.util.Date;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/8/2
 * @Description:
 */
public interface IProductService {

    ServerResponse categoryGet(int pageNum, int pageSize);

    ServerResponse categoryGetForAndroid();

    ServerResponse firstCateGet();

    ServerResponse secondCateGet();

    ServerResponse thirdCateGet();

    ServerResponse firstCateAdd(ProductionFirstCate firstCate);

    ServerResponse secondCateAdd(ProductionSecondCate secondCate);

    ServerResponse thirdCateAdd(ProductionThirdCate thirdCate);

    /**
     * 类别删除
     * @param id
     * @param flag 0为农作物，1为一类，2为二类，3为三类
     * @return
     */
    ServerResponse productionDel(int id, int flag);

    ServerResponse productBasicAdd(ProductBasic productBasic, int userId);

    ServerResponse productBasicUpdate(ProductBasic productBasic);

    ServerResponse productBasicGet(int userId);

    ServerResponse productStockAdd(ProductStock productStock, int userId);

    ServerResponse productStockGet(int userId);

    ServerResponse productStockGetByProductId(int userId, int productId);

    ServerResponse productOut(ProductOut productOut, int userId);

    ServerResponse productOutGetBySource(int userId);

    ServerResponse productOutGetByProductId(int productId);

    ServerResponse machineAdd(MachineVO machineVO, int userId);

    ServerResponse machineGet(int userId);

    ServerResponse getAllStream(int userId);
}
