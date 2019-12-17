package com.njs.agriculture.service;

import com.njs.agriculture.VO.MachineVO;
import com.njs.agriculture.bo.MachineBO;
import com.njs.agriculture.pojo.Machining;
import org.springframework.stereotype.Service;

/**
 * @author: chips
 * @date: 2019-12-17
 * @description:
 **/
@Service
public interface IRootRecordService {
    void recordRoot(MachineBO machining);
}
