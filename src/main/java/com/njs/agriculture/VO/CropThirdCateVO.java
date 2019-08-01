package com.njs.agriculture.VO;

import com.njs.agriculture.pojo.CropInfo;
import com.njs.agriculture.pojo.CropThirdCate;
import lombok.Data;

import java.util.List;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/8/1
 * @Description:
 */
@Data
public class CropThirdCateVO extends CropThirdCate {
    private List<CropInfo> cropInfoList;
}
