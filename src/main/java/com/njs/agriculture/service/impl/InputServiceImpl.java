package com.njs.agriculture.service.impl;

import com.google.common.collect.Lists;
import com.njs.agriculture.VO.InputCategoryVO;
import com.njs.agriculture.VO.InputVO;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.mapper.*;
import com.njs.agriculture.pojo.*;
import com.njs.agriculture.service.IInputService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/25
 * @Description:
 */
@Service("iInputservice")
public class InputServiceImpl implements IInputService {

    @Autowired
    InputFirstCateMapper inputFirstCateMapper;

    @Autowired
    InputSecondCateMapper inputSecondCateMapper;

    @Autowired
    InputUserMapper inputUserMapper;

    @Autowired
    InputEnterpriseMapper inputEnterpriseMapper;

    @Autowired
    InputPurchaseMapper inputPurchaseMapper;

    @Autowired
    InputConsumeMapper inputConsumeMapper;


    @Override
    public ServerResponse purchase(InputVO inputVO) {
        int source = inputVO.getSource();
        //0为用户添加,1为企业添加， 10为用户从企业领用
        //sourceId企业则为企业id，个人则为企业投入品对应的id
        //1.录入投入品
        //2.写入购入表 0和1
        // 或者领用表 10
        int resultRount = 0;
        if(source == 0){
            InputUser inputUser = new InputUser();
            BeanUtils.copyProperties(inputVO, inputUser);
            inputUser.setSource(0);
            inputUser.setSourceId(null);
            resultRount = inputUserMapper.insert(inputUser);
            if(resultRount == 0){
                return ServerResponse.createByErrorMessage("农资信息表录入失败！");
            }
            int id = inputUser.getId();
            InputPurchase inputPurchase = new InputPurchase();
            inputPurchase.setPrice(inputVO.getPrice());
            inputPurchase.setQuantity(inputVO.getQuantity());
            inputPurchase.setInputSource(0);
            inputPurchase.setSourceId(id);
            resultRount = inputPurchaseMapper.insert(inputPurchase);
        }else if(source == 1){
            InputEnterprise inputEnterprise = new InputEnterprise();
            BeanUtils.copyProperties(inputVO, inputEnterprise);
            inputEnterprise.setEnterpriseId(inputVO.getSourceId());
            resultRount = inputEnterpriseMapper.insert(inputEnterprise);
            if(resultRount == 0){
                return ServerResponse.createByErrorMessage("农资信息表录入失败！");
            }
            int id = inputEnterprise.getId();
            InputPurchase inputPurchase = new InputPurchase();
            inputPurchase.setPrice(inputVO.getPrice());
            inputPurchase.setQuantity(inputVO.getQuantity());
            inputPurchase.setInputSource(1);
            inputPurchase.setSourceId(id);
            resultRount = inputPurchaseMapper.insert(inputPurchase);
        }else if(source == 10){
            InputUser inputUser = new InputUser();
            BeanUtils.copyProperties(inputVO, inputUser);
            inputUser.setSource(1);
            inputUser.setSourceId(null);
            resultRount = inputUserMapper.insert(inputUser);
            if(resultRount == 0){
                return ServerResponse.createByErrorMessage("农资信息表录入失败！");
            }
            int id = inputUser.getId();
            InputConsume inputConsume = new InputConsume();
            inputConsume.setUserinputId(id);
            inputConsume.setEnterpriseinputId(inputVO.getSourceId());
            inputConsume.setQuantity(inputVO.getQuantity());
            resultRount = inputConsumeMapper.insert(inputConsume);
        }
        if(resultRount == 0){
            return ServerResponse.createByErrorMessage("农资购入表或者领用录入失败！");
        }
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse<List<InputCategoryVO>> categoryInfo() {
        List<InputCategoryVO> inputCategoryVOS = Lists.newArrayList();
        //1.先获取一级类别
        List<InputFirstCate> inputFirstCateList = inputFirstCateMapper.selectAll();
        //2.然后循环获取二级类别
        for (InputFirstCate inputFirstCate : inputFirstCateList) {
            InputCategoryVO inputCategoryVO = new InputCategoryVO();
            List<InputSecondCate> inputSecondCateList = inputSecondCateMapper.selectByFirstCate(inputFirstCate.getId());
            inputCategoryVO.setInputFirstCate(inputFirstCate);
            inputCategoryVO.setInputSecondCates(inputSecondCateList);
            inputCategoryVOS.add(inputCategoryVO);
        }
        //3.构造类别对象
        return ServerResponse.createBySuccess(inputCategoryVOS);
    }

    @Override
    public ServerResponse infoGet(int firstCateId, int secondCateId, String order, String flag, int sourceId, int source) {
        return null;
    }

    @Override
    public ServerResponse StockRemind(int sourceId, int source, int type, int threshold) {
        return null;
    }
}
