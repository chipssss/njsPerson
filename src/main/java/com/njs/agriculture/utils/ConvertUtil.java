package com.njs.agriculture.utils;

import com.google.common.base.Joiner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/9/10
 * @Description:
 */
public class ConvertUtil {
    public static List<String> string2List(String s){
        String[] array = s.split(",");
        return Arrays.asList(array);
    }

    public static String list2String(List<String> list){
        return Joiner.on(",").join(list);
    }
}
