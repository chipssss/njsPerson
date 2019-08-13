package com.njs.agriculture.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.njs.agriculture.VO.*;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.mapper.*;
import com.njs.agriculture.pojo.*;
import com.njs.agriculture.service.IProductService;
import com.njs.agriculture.service.IUserService;
import com.njs.agriculture.utils.MathUtil;
import net.sf.jsqlparser.schema.Server;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/8/2
 * @Description:
 */
@Service("iProductService")
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductBasicMapper productBasicMapper;

    @Autowired
    private ProductionFirstCateMapper productionFirstCateMapper;

    @Autowired
    private ProductionSecondCateMapper productionSecondCateMapper;

    @Autowired
    private ProductionThirdCateMapper productionThirdCateMapper;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ProductStockMapper productStockMapper;

    @Autowired
    private ProductOutMapper productOutMapper;

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
        PageInfo pageResult = new PageInfo(thirdCateList);
        pageResult.setList(thirdCateVOList);
        return ServerResponse.createBySuccess(pageResult);
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
    public ServerResponse firstCateAdd(ProductionFirstCate firstCate) {
        int resultRow = productionFirstCateMapper.insert(firstCate);
        if (resultRow == 0) {
            return ServerResponse.createByErrorMessage("插入失败！");
        }
        return ServerResponse.createBySuccess(firstCate);
    }

    @Override
    public ServerResponse secondCateAdd(ProductionSecondCate secondCate) {
        int resultRow = productionSecondCateMapper.insert(secondCate);
        if (resultRow == 0) {
            return ServerResponse.createByErrorMessage("插入失败！");
        }
        return ServerResponse.createBySuccess(secondCate);
    }

    @Override
    public ServerResponse thirdCateAdd(ProductionThirdCate thirdCate) {
        int resultRow = productionThirdCateMapper.insert(thirdCate);
        if (resultRow == 0) {
            return ServerResponse.createByErrorMessage("插入失败！");
        }
        return ServerResponse.createBySuccess(thirdCate);
    }

    @Override
    public ServerResponse productionDel(int id, int flag) {
        int resultRow = 0;
        if (flag == 1) {
            resultRow = productionFirstCateMapper.deleteByPrimaryKey(id);
        } else if (flag == 2) {
            resultRow = productionSecondCateMapper.deleteByPrimaryKey(id);
        } else if (flag == 3) {
            resultRow = productionThirdCateMapper.deleteByPrimaryKey(id);
        } else if (flag == 0) {
            resultRow = productBasicMapper.deleteByPrimaryKey(id);
        }
        if (resultRow == 0) {
            return ServerResponse.createByErrorMessage("删除失败！");
        }
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse productBasicAdd(ProductBasic productBasic, int userId) {
        ServerResponse<Map> serverResponse = iUserService.isManager(userId);

        productBasic.setSource((int) serverResponse.getData().get("source"));
        productBasic.setSourceId((int) serverResponse.getData().get("sourceId"));

        int resultRow = productBasicMapper.insert(productBasic);
        if (resultRow == 0) {
            return ServerResponse.createByErrorMessage("插入新数据失败！");
        }
        return ServerResponse.createBySuccess(productBasic);
    }

    @Override
    public ServerResponse productBasicUpdate(ProductBasic productBasic) {
        int resultRow = productBasicMapper.updateByPrimaryKeySelective(productBasic);
        if (resultRow == 0) {
            return ServerResponse.createByErrorMessage("更新失败！");
        }
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse productBasicGet(int userId) {
        ServerResponse<Map> serverResponse = iUserService.isManager(userId);
        List<ProductBasic> productBasicList;
        productBasicList = productBasicMapper.selectBySource((int) serverResponse.getData().get("source"), (int) serverResponse.getData().get("sourceId"));
        return ServerResponse.createBySuccess(productBasicList);
    }

    @Override
    public ServerResponse productStockAdd(ProductStock productStock, int userId) {

        ServerResponse<Map> serverResponse = iUserService.isManager(userId);
        productStock.setSource((int) serverResponse.getData().get("source"));
        productStock.setSourceId((int) serverResponse.getData().get("sourceId"));

        int resultRow = productStockMapper.insert(productStock);
        if (resultRow == 0) {
            return ServerResponse.createByErrorMessage("插入新数据失败！");
        }
        return ServerResponse.createBySuccess(productStock);
    }

    @Override
    public ServerResponse productStockGet(int userId) {
        ServerResponse<Map> serverResponse = iUserService.isManager(userId);
        List<ProductStock> productStockList;

        productStockList = productStockMapper.selectBySource((int) serverResponse.getData().get("source"), (int) serverResponse.getData().get("sourceId"));

        return ServerResponse.createBySuccess(productStockList);
    }

    @Override
    @Transactional
    public ServerResponse productOut(ProductOut productOut, int userId) {
        ProductStock productStock = productStockMapper.selectByPrimaryKey(productOut.getStockId());
        if(productStock == null){
            return ServerResponse.createByErrorMessage("库存不存在");
        }
        int result = productStock.getQuantity() - productOut.getQuantity();
        if (productOut.getQuantity() <= 0 || result < 0) {
            return ServerResponse.createByErrorMessage("输入数量错误！");
        }

        productStock.setQuantity(result);
        productStockMapper.updateByPrimaryKeySelective(productStock);

        ServerResponse<Map> serverResponse = iUserService.isManager(userId);
        productOut.setSource((int) serverResponse.getData().get("source"));
        productOut.setSourceId((int) serverResponse.getData().get("sourceId"));

        productOut.setProductId(productStock.getProductId());

        productOutMapper.insert(productOut);

        return ServerResponse.createBySuccess(productOut);
    }

    @Override
    public ServerResponse productOutGetBySource(int userId) {
        ServerResponse<Map> serverResponse = iUserService.isManager(userId);
        int source = (int) serverResponse.getData().get("source");
        int sourceId = (int) serverResponse.getData().get("sourceId");

        List<ProductOut> productOutList = productOutMapper.selectBySource(source, sourceId);
        return ServerResponse.createBySuccess(productOut2productOutVO(productOutList));
    }

    @Override
    public ServerResponse productOutGetByProductId(int productId) {
        return ServerResponse.createBySuccess(productOut2productOutVO(productOutMapper.selectByProductId(productId)));
    }

    public List<ProductOutVO> productOut2productOutVO(List<ProductOut> productOutList){
        List<ProductOutVO> productOutVOList = Lists.newLinkedList();
        for (ProductOut productOut : productOutList) {
            ProductOutVO productOutVO = new ProductOutVO();
            BeanUtils.copyProperties(productOut, productOutVO);
            productOutVOList.add(productOutVO);
            ProductBasic productBasic = productBasicMapper.selectByPrimaryKey(productOut.getProductId());
            if(productBasic != null){
                productOutVO.setProductName(productBasic.getName());
            }
        }
        return productOutVOList;
    }

}
