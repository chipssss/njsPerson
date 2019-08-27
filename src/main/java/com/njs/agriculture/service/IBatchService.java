package com.njs.agriculture.service;

import com.njs.agriculture.VO.BatchInfoVO;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.pojo.ProductionBatch;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/30
 * @Description:
 */
public interface IBatchService {
    /**
     * 获取田块批次
     * @param fieldId
     * @param finished 是否已加工
     * @return
     */
    ServerResponse batchInfoByFinished(int fieldId, int finished);

    /**
     * 获取田块批次
     * @param fieldId
     * @param generated 溯源与否
     * @return
     */
    ServerResponse batchInfoByGenerated(int fieldId, int generated);

    ServerResponse batchAdd(BatchInfoVO batchInfoVO);

    ServerResponse batchDel(int id);
}
