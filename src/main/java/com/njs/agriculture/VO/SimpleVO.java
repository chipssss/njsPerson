package com.njs.agriculture.VO;

import lombok.Data;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/8/14
 * @Description:
 */
@Data
public class SimpleVO {

    private int id;

    private String name;

    public SimpleVO(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
