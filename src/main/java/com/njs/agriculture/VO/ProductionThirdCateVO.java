package com.njs.agriculture.VO;

import lombok.Data;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/8/8
 * @Description: 用来做数据规范化
 */
@Data
public class ProductionThirdCateVO {

    private int firstCateId;

    private String firstCateName;

    private int secondCateId;

    private String secondCateName;

    private int thirdCateId;

    private String thirdCateName;
}
