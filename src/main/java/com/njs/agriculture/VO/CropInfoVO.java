package com.njs.agriculture.VO;

import com.njs.agriculture.pojo.CropInfo;
import lombok.Data;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/8/8
 * @Description:
 */
@Data
public class CropInfoVO{

    private int firstCateId;

    private String firstCateName;

    private int secondCateId;

    private String secondCateName;

    private int thirdCateId;

    private String thirdCateName;

    private int cropInfoId;

    private String cropInfoName;
}
