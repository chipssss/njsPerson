package com.njs.agriculture.VO;

import com.njs.agriculture.pojo.ProductionSecondCate;
import com.njs.agriculture.pojo.ProductionThirdCate;
import lombok.Data;

import java.util.List;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/8/2
 * @Description:
 */
@Data
public class ProductSecondCateVO extends ProductionSecondCate {
    private List<ProductThirdCateVO> thirdCateList;
}
