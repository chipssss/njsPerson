package com.njs.agriculture.VO;

import com.njs.agriculture.pojo.InputFirstCate;
import com.njs.agriculture.pojo.InputSecondCate;
import lombok.Data;

import java.util.List;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/25
 * @Description:
 */
@Data
public class InputCategoryVO {

    private InputFirstCate inputFirstCate;

    private List<InputSecondCate> inputSecondCates;


}
