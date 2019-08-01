package com.njs.agriculture.VO;


import com.njs.agriculture.pojo.CropSecondCate;

import lombok.Data;

import java.util.List;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/8/1
 * @Description:
 */
@Data
public class CropSecondCateVO extends CropSecondCate {
    private List<CropThirdCateVO> thirdCateList;

}
