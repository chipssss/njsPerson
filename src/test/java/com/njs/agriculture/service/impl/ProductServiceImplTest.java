package com.njs.agriculture.service.impl;

import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.mapper.ProductStockMapper;
import com.njs.agriculture.pojo.ProductStock;
import com.njs.agriculture.service.IProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/9/12
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceImplTest {

    @Autowired
    IProductService iProductService;

    @Autowired
    ProductStockMapper productStockMapper;

    @Test
    public void getAllStreamThread() {
        for (int i = 0;i < 10;i++){
            ServerResponse response = iProductService.getAllStream(1);
            System.out.println();

        }
    }
}