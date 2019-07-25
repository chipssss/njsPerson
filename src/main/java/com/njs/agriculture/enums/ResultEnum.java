package com.njs.agriculture.enums;

import lombok.Getter;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/11
 * @Description:
 */
@Getter
public enum  ResultEnum {
    SUCCESS(0, "成功"),

    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
