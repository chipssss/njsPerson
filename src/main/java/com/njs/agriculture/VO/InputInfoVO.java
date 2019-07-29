package com.njs.agriculture.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/29
 * @Description:
 */
@Data
public class InputInfoVO {

    private Integer id;

    private Integer categoryId;

    private String name;

    private Float quantity;

    private String specification;

    private BigDecimal price;

    @JsonFormat(pattern = "yyyy年MM月dd日",timezone = "GMT+8")
    private Date productionTime;

    private Integer shelfLife;

    private String manufacturer;

    private String remark;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm",timezone = "GMT+8")
    private Date createTime;

    private String personOrEnterpriseName;

    //来源,默认为0用户添加，1企业领用
    private Integer source;

    private Integer sourceId;
}
