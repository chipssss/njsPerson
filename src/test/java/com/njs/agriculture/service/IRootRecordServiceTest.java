package com.njs.agriculture.service;

import com.njs.agriculture.bo.MachineBO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author: chips
 * @date: 2019-12-17
 * @description:
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class IRootRecordServiceTest {
    @Autowired
    IRootRecordService iRootRecordService;

    @Test
    public void testDoRecord() {
        // 测试加工
        iRootRecordService.recordRoot(new MachineBO(null, null, null, null, null,
                null, null, null, new Date(), null, 1, 0, 1, null));
        // 测试包装，无批次
        iRootRecordService.recordRoot(new MachineBO(null, null, null, null, null,
                null, null, null, new Date(), null, 1, 1, 1, null));
        // 测试包装，有批次
        iRootRecordService.recordRoot(new MachineBO(null, null, null, null, null,
                null, null, null, new Date(), null, 1, 1, 1, "PC1111"));
    }
}