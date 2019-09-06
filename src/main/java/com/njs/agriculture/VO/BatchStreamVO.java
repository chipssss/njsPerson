package com.njs.agriculture.VO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.njs.agriculture.pojo.ProductionBatch;
import lombok.Data;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/9/6
 * @Description:
 */
@Data
@JsonSerialize(include =  JsonSerialize.Inclusion.NON_NULL)
public class BatchStreamVO extends ProductionBatch {
    private String fieldName;

    private String cropName;
}
