package com.njs.agriculture.service.impl.production;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.njs.agriculture.VO.ProductCateVO;
import com.njs.agriculture.VO.ProductSecondCateVO;
import com.njs.agriculture.VO.ProductThirdCateVO;
import com.njs.agriculture.base.BaseService;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.dto.ProductionDTO;
import com.njs.agriculture.mapper.*;
import com.njs.agriculture.pojo.ProductionFirstCate;
import com.njs.agriculture.pojo.ProductionSecondCate;
import com.njs.agriculture.pojo.ProductionThirdCate;
import com.njs.agriculture.service.IProductCateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: chips
 * @date: 2020-01-12
 * @description:
 **/
@Slf4j
@Service("IProductCateService")
public class ProductCateImpl extends BaseService implements IProductCateService {

    @Autowired
    private ProductionFirstCateMapper productionFirstCateMapper;

    @Autowired
    private ProductionSecondCateMapper productionSecondCateMapper;

    @Autowired
    private ProductionThirdCateMapper productionThirdCateMapper;

    @Autowired
    private ProductDTOMapper productDTOMapper;


    @Override
    public ServerResponse add(String name, int userId) {
        ProductionThirdCate thirdCate = new ProductionThirdCate(null, name, 0, 0, userId);
        int result = productionThirdCateMapper.insertWithGenerateKey(thirdCate); // 返回主键
        if (isDOError(result)) {
            return ServerResponse.createByErrorMessage("添加类别失败");
        }
        return ServerResponse.createBySuccess("添加类别成功", thirdCate);
    }

    private static final int CATE_OTHER_ID = 0;
    @Override
    public ServerResponse getForAndroid(int userId) {
        List<ProductCateVO> cateVOS = Lists.newLinkedList();
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
                // 当第一类别其他时，需根据用户筛选出用户自定义的
                List<ProductionThirdCate> thirdCates = firstCate.getId() != CATE_OTHER_ID?
                        productionThirdCateMapper.selectBySecondCateId(secondCate.getId()) :
                        productionThirdCateMapper.selectUserDiyCate(userId);

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
        }
        return ServerResponse.createBySuccess(cateVOS);
    }

    @Override
    public ServerResponse categoryGet(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProductionDTO> productionDTOList = productDTOMapper.selectAll();
        PageInfo pageResult = new PageInfo(productionDTOList);
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

}
