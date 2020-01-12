package com.njs.agriculture.base;

/**
 * @author: chips
 * @date: 2020-01-12
 * @description:
 **/
public class BaseService {
    /* 数据库操作是否成功 */
    protected boolean isDOError(int result) {
        return result == 0;
    }
}
