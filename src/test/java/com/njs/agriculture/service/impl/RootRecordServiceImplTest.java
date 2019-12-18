package com.njs.agriculture.service.impl;

import com.njs.agriculture.service.IBatchCodeService;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author: chips
 * @date: 2019-12-18
 * @description:
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class RootRecordServiceImplTest {

    @Autowired
    IBatchCodeService iBatchCodeService;
    @Test
    public void getBatchNum() {
        System.out.println(iBatchCodeService.getBatchNum(0));
        System.out.println(iBatchCodeService.getBatchNum(1));
    }
}