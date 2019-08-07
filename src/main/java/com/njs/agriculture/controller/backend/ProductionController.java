package com.njs.agriculture.controller.backend;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.pojo.ProductPool;
import com.njs.agriculture.pojo.ProductionFirstCate;
import com.njs.agriculture.pojo.ProductionSecondCate;
import com.njs.agriculture.pojo.ProductionThirdCate;
import com.njs.agriculture.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/8/2
 * @Description:
 */
@RestController
@RequestMapping("/backend/production/")
public class ProductionController {

    @Autowired
    private IProductService iProductService;

    @PostMapping("categoryGet.do")
    public ServerResponse categoryGet(@RequestBody JSONObject jsonObject){
        int pageNum = jsonObject.getIntValue("pageNum");
        int pageSize = jsonObject.getIntValue("pageSize");
        return iProductService.categoryGet(pageNum, pageSize);
    }

    @PostMapping("productAdd.do")
    public ServerResponse productAdd(@RequestBody ProductPool productPool){
        return iProductService.productAdd(productPool);
    }

    @RequestMapping("firstCateAdd.do")
    public ServerResponse firstCateAdd(@RequestBody ProductionFirstCate firstCate){
        return iProductService.firstCateAdd(firstCate);
    }

    @RequestMapping("secondCateAdd.do")
    public ServerResponse secondCateAdd(@RequestBody ProductionSecondCate secondCate){
        return iProductService.secondCateAdd(secondCate);
    }

    @RequestMapping("thirdCateAdd.do")
    public ServerResponse thirdCateAdd(@RequestBody ProductionThirdCate thirdCate){
        return iProductService.thirdCateAdd(thirdCate);
    }

    @RequestMapping("productionDel.do")
    public ServerResponse productionDel(@RequestBody JSONObject jsonObject){
        int id = jsonObject.getIntValue("id");
        int flag = jsonObject.getIntValue("flag");
        return iProductService.productionDel(id, flag);
    }

}
