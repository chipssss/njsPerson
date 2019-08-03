package com.njs.agriculture.VO;

import com.njs.agriculture.pojo.ProductionFirstCate;
import lombok.Data;

import java.util.List;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/8/2
 * @Description:
 */
@Data
public class ProductCateVO extends ProductionFirstCate {
    private List<ProductSecondCateVO> secondCateVOList;
}
