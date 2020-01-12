package com.njs.agriculture.controller.backend;

import com.alibaba.fastjson.JSONObject;
import com.njs.agriculture.VO.MachineVO;
import com.njs.agriculture.VO.ProductStockVO;
import com.njs.agriculture.base.BaseController;
import com.njs.agriculture.common.Const;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.pojo.*;
import com.njs.agriculture.service.IBatchCodeService;
import com.njs.agriculture.service.IProductCateService;
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
public class ProductionController extends BaseController {

    @Autowired
    private IProductService iProductService;

    @Autowired
    IBatchCodeService iBatchCodeService;

    @Autowired
    private IProductCateService productCateService;

    /* 产品类别相关接口 */
    @PostMapping("categoryGet.do")
    public ServerResponse categoryGet(@RequestBody JSONObject jsonObject){
        int pageNum = jsonObject.getIntValue("pageNum");
        int pageSize = jsonObject.getIntValue("pageSize");
        return productCateService.categoryGet(pageNum, pageSize);
    }

    @PostMapping("categoryAdd.do")
    public ServerResponse categoryAdd(HttpSession session, @RequestBody JSONObject jsonObject){
        return productCateService.add(jsonObject.getString("name"), getUserIdBySession(session));
    }

    @GetMapping("categoryGet.do")
    public ServerResponse categoryGet(HttpSession session){
        return productCateService.getForAndroid(getUserIdBySession(session));
    }

    @GetMapping("firstCateGet.do")
    public ServerResponse firstCateGet(){
        return productCateService.firstCateGet();
    }

    @GetMapping("secondCateGet.do")
    public ServerResponse secondCateGet(){
        return productCateService.secondCateGet();
    }

    @GetMapping("thirdCateGet.do")
    public ServerResponse thirdCateGet(){
        return productCateService.thirdCateGet();
    }

    @RequestMapping("firstCateAdd.do")
    public ServerResponse firstCateAdd(@RequestBody ProductionFirstCate firstCate){
        return productCateService.firstCateAdd(firstCate);
    }

    @RequestMapping("secondCateAdd.do")
    public ServerResponse secondCateAdd(@RequestBody ProductionSecondCate secondCate){
        return productCateService.secondCateAdd(secondCate);
    }

    @RequestMapping("thirdCateAdd.do")
    public ServerResponse thirdCateAdd(@RequestBody ProductionThirdCate thirdCate){
        return productCateService.thirdCateAdd(thirdCate);
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
    public ServerResponse productStockAdd(@RequestBody ProductStockVO productStockVO, HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        return iProductService.productStockAdd(productStockVO, user.getUserId());
    }

    //库存get
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
    public ServerResponse productOut(@RequestBody ProductOut productOut, HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        return iProductService.productOut(productOut, user.getUserId());
    }

    //出库流水
    @GetMapping("productOutGetBySource.do")
    public ServerResponse productOut( HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        return iProductService.productOutGetBySource(user.getUserId());
    }

    @GetMapping("productOutGetByProductId.do")
    public ServerResponse productOut(int productId){
        return iProductService.productOutGetByProductId(productId);
    }

    @PostMapping("productMachine.do")
    public ServerResponse productMachine(@RequestBody MachineVO machineVO, HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        return iProductService.machineAdd(machineVO, user.getUserId());
    }

    @GetMapping("getAllStream.do")
    public ServerResponse getAllStream(HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        return iProductService.getAllStream(user.getUserId());
    }


    @GetMapping("machineGet.do")
    public ServerResponse machineGet(HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        return iProductService.machineGet(user.getUserId());
    }

    @GetMapping("getAllMachineOperation.do")
    public ServerResponse getAllMachineOperation(){
        return iProductService.getAllMachineOperation();
    }

    @GetMapping("getTeaStock.do")
    public ServerResponse getTeaStock(HttpSession httpSession){
        User user = (User)httpSession.getAttribute(Const.CURRENT_USER);
        return iProductService.getTeaStock(user.getUserId());
    }

    @GetMapping("getBatchNumList.do")
    public ServerResponse getBatchNumList(Integer fieldId, HttpSession httpSession) {
        User user = (User)httpSession.getAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess(iBatchCodeService.getBatchNum(fieldId, user.getUserId()));
    }

}
