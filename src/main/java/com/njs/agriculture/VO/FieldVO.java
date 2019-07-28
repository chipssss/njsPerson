package com.njs.agriculture.VO;

import lombok.Data;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/28
 * @Description:
 */
@Data
public class FieldVO {

    private Float square;

    private String name;

    private String manager;

    private String location;

    private String remark;

    private boolean isFree;

    private int userId;

    private boolean isPerson;
}
