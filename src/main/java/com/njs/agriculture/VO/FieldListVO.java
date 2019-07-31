package com.njs.agriculture.VO;

import lombok.Data;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/31
 * @Description:
 */
@Data
public class FieldListVO {

    private int id;

    private String name;

    public FieldListVO() {
    }

    public FieldListVO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
