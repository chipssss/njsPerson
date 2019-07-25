package com.njs.agriculture.VO;

import lombok.Data;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/11
 * @Description:
 */
@Data
public class ResultVO<T> {
    /** 错误码. */
    private Integer code;

    /** 提示信息. */
    private String msg;

    /** 具体内容. */
    private T data;
}
