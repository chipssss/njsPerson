package com.njs.agriculture.VO;

import com.njs.agriculture.pojo.ProductOut;
import lombok.Data;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/8/13
 * @Description:
 */
@Data
public class ProductOutVO extends ProductOut {
    private String productName;

    private String batchNum;
}
