package com.njs.agriculture.VO;

import com.njs.agriculture.pojo.InputStream;
import lombok.Data;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/9/6
 * @Description:
 */
@Data
public class InputStreamVO extends InputStream {
    private String fieldName;

    private String cropName;
}
