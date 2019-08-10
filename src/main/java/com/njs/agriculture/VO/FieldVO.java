package com.njs.agriculture.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/28
 * @Description:
 */
@Data
public class FieldVO {

    private int id;

    private Float square;

    private String name;

    private String manager;

    private String location;

    private String remark;

    @JsonProperty("isFree")
    private boolean isFree;

    private int userId;

    @JsonProperty("isPerson")
    private boolean isPerson;

    private int cropId;
}
