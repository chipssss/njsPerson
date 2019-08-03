package com.njs.agriculture.VO;

import com.njs.agriculture.pojo.CropFirstCate;
import lombok.Data;

import java.util.List;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/8/1
 * @Description:
 */
@Data
public class CropCateVO extends CropFirstCate{
    private List<CropSecondCateVO> secondCateList;
}
