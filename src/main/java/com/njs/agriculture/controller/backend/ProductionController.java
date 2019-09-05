package com.njs.agriculture.controller.backend;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.njs.agriculture.common.Const;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.pojo.*;
import com.njs.agriculture.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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

    @GetMapping("categoryGet.do")
    public ServerResponse categoryGet(){
        return iProductService.categoryGetForAndroid();
    }

    @GetMapping("firstCateGet.do")
    public ServerResponse firstCateGet(){
        return iProductService.firstCateGet();
    }

    @GetMapping("secondCateGet.do")
    public ServerResponse secondCateGet(){
        return iProductService.secondCateGet();
    }

    @GetMapping("thirdCateGet.do")
    public ServerResponse thirdCateGet(){
        return iProductService.thirdCateGet();
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

    /*基础栏目和库存操作*/
    @RequestMapping("productBasicAdd.do")
    public ServerResponse productBasicAdd(@RequestBody ProductBasic productBasic, HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        return iProductService.productBasicAdd(productBasic, user.getUserId());
    }

    @RequestMapping("productBasicUpdate.do")
    public ServerResponse productBasicUpdate(@RequestBody ProductBasic productBasic){
        return iProductService.productBasicUpdate(productBasic);
    }

    @GetMapping("productBasicGet.do")
    public ServerResponse productBasicGet(HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        return iProductService.productBasicGet(user.getUserId());
    }

    @RequestMapping("productStockAdd.do")
    public ServerResponse productStockAdd(@RequestBody ProductStock productStock, HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        return iProductService.productStockAdd(productStock, user.getUserId());
    }

    @RequestMapping("productStockGet.do")
    public ServerResponse productStockGet(HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        return iProductService.productStockGet(user.getUserId());
    }

    @RequestMapping("productStockGetByProductId.do")
    public ServerResponse productStockGetByProductId(int productId, HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        return iProductService.productStockGetByProductId(user.getUserId(), productId);
    }

    @RequestMapping("productOut.do")
    public ServerResponse productOut( @RequestBody ProductOut productOut, HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        return iProductService.productOut(productOut, user.getUserId());
    }

    @GetMapping("productOutGetBySource.do")
    public ServerResponse productOut( HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        return iProductService.productOutGetBySource(user.getUserId());
    }

    @GetMapping("productOutGetByProductId.do")
    public ServerResponse productOut(int productId){
        return iProductService.productOutGetByProductId(productId);
    }
}
