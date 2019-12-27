package com.njs.agriculture.service.impl;

import com.njs.agriculture.service.IActivationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author: chips
 * @date: 2019-12-27
 * @description:
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class ActivationServiceImplTest {

    @Autowired
    IActivationService iActivationService;

    @Test
    public void scanGetRecords() {
        iActivationService.scanGetRecords("DW1220191219");

    }
}