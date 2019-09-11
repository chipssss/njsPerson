package com.njs.agriculture.VO;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/9/10
 * @Description:
 */
public interface VOConvert<S, T>{
    T convert(S s);
}
