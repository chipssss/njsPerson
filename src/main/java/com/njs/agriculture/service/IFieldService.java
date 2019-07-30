package com.njs.agriculture.service;

import com.njs.agriculture.VO.FieldVO;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.pojo.Field;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/30
 * @Description:
 */
public interface IFieldService {
    ServerResponse addField(FieldVO fieldVO);

    ServerResponse delField(int fieldId);

    ServerResponse modifyField(Field field);

    /**
     * 田块获取
     * @param userId
     * @return 普通用户返回个人田块，企业负责人返回企业所有田块
     */
    ServerResponse fieldInfo(int userId);
}
