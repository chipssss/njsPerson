package com.njs.agriculture.VO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/25
 * @Description:
 */
@Data
public class InputVO {

    private Integer categoryId;

    private String name;

    private Integer quantity;

    private String specification;

    private BigDecimal price;

    private Date productionTime;

    private Integer shelfLife;

    private String manufacturer;

    private String remark;

    private Integer source;

    private Integer sourceId;

    private Integer userId;



}
