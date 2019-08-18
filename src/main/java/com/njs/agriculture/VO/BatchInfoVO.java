package com.njs.agriculture.VO;

import com.njs.agriculture.pojo.ProductionBatch;
import lombok.Data;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/30
 * @Description:
 */
@Data
public class BatchInfoVO extends ProductionBatch {
    private int recoveryRecordId;
}
