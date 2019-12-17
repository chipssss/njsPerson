package com.njs.agriculture.service;

import org.springframework.stereotype.Service;

/**
 * @author: chips
 * @date: 2019-12-17
 * @description: 批次码相关
 **/
@Service
public interface IBatchCodeService {
    /**
     * 生成格式 PC+田块id+用户id+时间(yyyyMMdd)+2位变码
     * @param fieldId
     * @param userId
     * @return
     */
    String generateCode(int fieldId, int userId);
}
