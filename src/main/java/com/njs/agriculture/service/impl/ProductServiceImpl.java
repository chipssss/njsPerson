package com.njs.agriculture.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.njs.agriculture.VO.ProductCateVO;
import com.njs.agriculture.VO.ProductSecondCateVO;
import com.njs.agriculture.VO.ProductThirdCateVO;
import com.njs.agriculture.VO.ProductionThirdCateVO;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.mapper.ProductPoolMapper;
import com.njs.agriculture.mapper.ProductionFirstCateMapper;
import com.njs.agriculture.mapper.ProductionSecondCateMapper;
import com.njs.agriculture.mapper.ProductionThirdCateMapper;
import com.njs.agriculture.pojo.ProductPool;
import com.njs.agriculture.pojo.ProductionFirstCate;
import com.njs.agriculture.pojo.ProductionSecondCate;
import com.njs.agriculture.pojo.ProductionThirdCate;
import com.njs.agriculture.service.IProductService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/8/2
 * @Description:
 */
@Service("iProductService")
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductPoolMapper productPoolMapper;

    @Autowired
    private ProductionFirstCateMapper productionFirstCateMapper;

    @Autowired
    private ProductionSecondCateMapper productionSecondCateMapper;

    @Autowired
    private ProductionThirdCateMapper productionThirdCateMapper;

    @Override
    public ServerResponse categoryGet(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        /*List<ProductCateVO> cateVOS = Lists.newLinkedList();
        List<ProductionFirstCate> firstCates = productionFirstCateMapper.selectAll();
        for (ProductionFirstCate firstCate : firstCates) {
            ProductCateVO productCateVO = new ProductCateVO();
            BeanUtils.copyProperties(firstCate, productCateVO);

            List<ProductSecondCateVO> secondCateVOS = Lists.newLinkedList();
            List<ProductionSecondCate> secondCates = productionSecondCateMapper.selectByFirstCateId(firstCate.getId());
            for (ProductionSecondCate secondCate : secondCates) {
                ProductSecondCateVO productSecondCateVO = new ProductSecondCateVO();
                BeanUtils.copyProperties(secondCate, productSecondCateVO);

                List<ProductThirdCateVO> thirdCateVOS = Lists.newLinkedList();
                List<ProductionThirdCate> thirdCates = productionThirdCateMapper.selectBySecondCateId(secondCate.getId());
                for (ProductionThirdCate thirdCate : thirdCates) {
                    ProductThirdCateVO productThirdCateVO = new ProductThirdCateVO();
                    BeanUtils.copyProperties(thirdCate, productThirdCateVO);
                    thirdCateVOS.add(productThirdCateVO);
                }
                productSecondCateVO.setThirdCateList(thirdCateVOS);
                secondCateVOS.add(productSecondCateVO);
            }
            productCateVO.setSecondCateVOList(secondCateVOS);
            cateVOS.add(productCateVO);
        }*/

        List<ProductionThirdCate> thirdCateList = productionThirdCateMapper.selectAll();
        List<ProductionThirdCateVO> thirdCateVOList = Lists.newLinkedList();
        for (ProductionThirdCate productionThirdCate : thirdCateList) {
            ProductionThirdCateVO thirdCateVO = new ProductionThirdCateVO();
            thirdCateVO.setThirdCateId(productionThirdCate.getId());
            thirdCateVO.setThirdCateName(productionThirdCate.getName());
            ProductionSecondCate secondCate = productionSecondCateMapper.selectByPrimaryKey(productionThirdCate.getSecondcateId());
            thirdCateVO.setSecondCateId(secondCate.getId());
            thirdCateVO.setSecondCateName(secondCate.getName());
            ProductionFirstCate firstCate = productionFirstCateMapper.selectByPrimaryKey(secondCate.getFirstcateId());
            thirdCateVO.setFirstCateId(firstCate.getId());
            thirdCateVO.setFirstCateName(firstCate.getName());
            thirdCateVOList.add(thirdCateVO);
        }

        return ServerResponse.createBySuccess(thirdCateVOList);
    }

    @Override
    public ServerResponse firstCateGet() {
        return ServerResponse.createBySuccess(productionFirstCateMapper.selectAll());
    }

    @Override
    public ServerResponse secondCateGet() {
        return ServerResponse.createBySuccess(productionSecondCateMapper.selectAll());
    }

    @Override
    public ServerResponse thirdCateGet() {
        return ServerResponse.createBySuccess(productionThirdCateMapper.selectAll());
    }

    @Override
    public ServerResponse productAdd(ProductPool productPool) {
        int resultRow = productPoolMapper.insert(productPool);
        if(resultRow == 0){
            return ServerResponse.createByErrorMessage("插入失败！");
        }
        return ServerResponse.createBySuccess(productPool);
    }

    @Override
    public ServerResponse firstCateAdd(ProductionFirstCate firstCate) {
        int resultRow = productionFirstCateMapper.insert(firstCate);
        if(resultRow == 0){
            return ServerResponse.createByErrorMessage("插入失败！");
        }
        return ServerResponse.createBySuccess(firstCate);
    }

    @Override
    public ServerResponse secondCateAdd(ProductionSecondCate secondCate) {
        int resultRow = productionSecondCateMapper.insert(secondCate);
        if(resultRow == 0){
            return ServerResponse.createByErrorMessage("插入失败！");
        }
        return ServerResponse.createBySuccess(secondCate);
    }

    @Override
    public ServerResponse thirdCateAdd(ProductionThirdCate thirdCate) {
        int resultRow = productionThirdCateMapper.insert(thirdCate);
        if(resultRow == 0){
            return ServerResponse.createByErrorMessage("插入失败！");
        }
        return ServerResponse.createBySuccess(thirdCate);
    }

    @Override
    public ServerResponse productionDel(int id, int flag) {
        int resultRow = 0;
        if(flag == 1){
            resultRow = productionFirstCateMapper.deleteByPrimaryKey(id);
        }else if(flag == 2){
            resultRow = productionSecondCateMapper.deleteByPrimaryKey(id);
        }else if(flag == 3){
            resultRow = productionThirdCateMapper.deleteByPrimaryKey(id);
        }else if(flag == 0){
            resultRow = productPoolMapper.deleteByPrimaryKey(id);
        }
        if(resultRow == 0){
            return ServerResponse.createByErrorMessage("删除失败！");
        }
        return ServerResponse.createBySuccess();
    }
}
