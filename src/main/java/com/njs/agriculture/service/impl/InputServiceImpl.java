package com.njs.agriculture.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.njs.agriculture.VO.InputCategoryVO;
import com.njs.agriculture.VO.InputInfoVO;
import com.njs.agriculture.VO.InputVO;
import com.njs.agriculture.common.Const;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.mapper.*;
import com.njs.agriculture.pojo.*;
import com.njs.agriculture.service.IInputService;
import com.njs.agriculture.utils.DateUtil;
import com.njs.agriculture.utils.MathUtil;
import net.sf.jsqlparser.schema.Server;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/25
 * @Description:
 */
@Service("iInputservice")
public class InputServiceImpl implements IInputService {

    @Autowired
    private InputReturnMapper inputReturnMapper;

    @Autowired
    EnterpriseMapper enterpriseMapper;

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
        if (source == 0) {
            InputUser inputUser = new InputUser();
            BeanUtils.copyProperties(inputVO, inputUser);
            inputUser.setSource(0);
            inputUser.setSourceId(null);
            resultRount = inputUserMapper.insert(inputUser);
            if (resultRount == 0) {
                return ServerResponse.createByErrorMessage("农资信息表录入失败！");
            }
            int id = inputUser.getId();
            InputPurchase inputPurchase = new InputPurchase();
            inputPurchase.setPrice(inputVO.getPrice());
            inputPurchase.setQuantity(inputVO.getQuantity());
            inputPurchase.setInputSource(0);
            inputPurchase.setSourceId(id);
            resultRount = inputPurchaseMapper.insert(inputPurchase);
        } else if (source == 1) {
            InputEnterprise inputEnterprise = new InputEnterprise();
            BeanUtils.copyProperties(inputVO, inputEnterprise);
            inputEnterprise.setEnterpriseId(inputVO.getSourceId());
            resultRount = inputEnterpriseMapper.insert(inputEnterprise);
            if (resultRount == 0) {
                return ServerResponse.createByErrorMessage("农资信息表录入失败！");
            }
            int id = inputEnterprise.getId();
            InputPurchase inputPurchase = new InputPurchase();
            inputPurchase.setPrice(inputVO.getPrice());
            inputPurchase.setQuantity(inputVO.getQuantity());
            inputPurchase.setInputSource(1);
            inputPurchase.setSourceId(id);
            resultRount = inputPurchaseMapper.insert(inputPurchase);
        } else if (source == 10) {
            InputUser inputUser = new InputUser();
            BeanUtils.copyProperties(inputVO, inputUser);
            inputUser.setSource(1);
            inputUser.setSourceId(null);
            resultRount = inputUserMapper.insert(inputUser);
            if (resultRount == 0) {
                return ServerResponse.createByErrorMessage("农资信息表录入失败！");
            }
            int id = inputUser.getId();
            InputConsume inputConsume = new InputConsume();
            inputConsume.setUserinputId(id);
            inputConsume.setEnterpriseinputId(inputVO.getSourceId());
            inputConsume.setQuantity(inputVO.getQuantity());
            resultRount = inputConsumeMapper.insert(inputConsume);
        }
        if (resultRount == 0) {
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

    /**
     * TODO 重复代码太多，看如何分离
     *
     * @param firstCateId  默认“所有”，即为0或不需要填写
     * @param secondCateId 默认“所有“，即为0或不需要填写，如果有二级类别id则不读取一级id
     * @param orderBy
     * @param sourceId
     * @param source       来源，0为用户，1为企业
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ServerResponse infoGet(int firstCateId, int secondCateId, String orderBy, int sourceId, int source, int pageNum, int pageSize) {

        //1.先检查2级目录id，没有就检查一级目录id，然后一次检查order，flag
        //2.检查source源，查不同的表 0为用户，1为企业
        //排序处理
        String[] orderByArray = null;
        String orderString = "create_time desc";
        if (StringUtils.isNotBlank(orderBy)) {
            if (Const.InputListOrderBy.PRICE_ASC_DESC.contains(orderBy) || Const.InputListOrderBy.QUANTITY_ASC_DESC.contains(orderBy)) {
                orderByArray = orderBy.split("_");
                orderString = orderByArray[0] + " " + orderByArray[1];
            } else if (Const.InputListOrderBy.TIME_ASC_DESC.contains(orderBy)) {
                orderByArray = orderBy.split("_");
                orderByArray = new String[]{orderByArray[0] + "_" + orderByArray[1], orderByArray[2]};
                orderString = orderByArray[0] + " " + orderByArray[1];
            }
        }
        return infoGet(secondCateId, firstCateId, pageNum, pageSize, orderString, source, sourceId);

    }


    private ServerResponse infoGet(int secondCateId, int firstCateId, int pageNum, int pageSize, String orderString, int source, int sourceId) {

        List<InputUser> inputUsers = Lists.newArrayList();
        List<InputEnterprise> inputEnterprises = Lists.newArrayList();
        if (secondCateId == 0) {
            if (firstCateId == 0) {
                //默认所有
                PageHelper.startPage(pageNum, pageSize);
                PageHelper.orderBy(orderString);
                if (source == Const.InputRole.USER) {
                    inputUsers = inputUserMapper.selectAll(sourceId);
                } else {
                    inputEnterprises = inputEnterpriseMapper.selectAll(sourceId);
                }
            } else {
                //查firstCateId
                List<InputSecondCate> inputSecondCateList = inputSecondCateMapper.selectByFirstCate(firstCateId);
                List<Integer> categoryList = Lists.newArrayList();
                for (InputSecondCate inputSecondCate : inputSecondCateList) {
                    categoryList.add(inputSecondCate.getId());
                }

                PageHelper.startPage(pageNum, pageSize);
                PageHelper.orderBy(orderString);

                if (source == Const.InputRole.USER) {
                    inputUsers = inputUserMapper.selectByCategoryIdList(sourceId, categoryList);
                } else {
                    inputEnterprises = inputEnterpriseMapper.selectByCategoryIdList(sourceId, categoryList);
                }
            }
        } else {
            //查二级id
            PageHelper.startPage(pageNum, pageSize);

            PageHelper.orderBy(orderString);

            if (source == Const.InputRole.USER) {
                inputUsers = inputUserMapper.selectByCategoryId(sourceId, secondCateId);
            } else {
                inputEnterprises = inputEnterpriseMapper.selectByCategoryId(sourceId, secondCateId);
            }
        }
        List<InputInfoVO> inputInfoVOS = Lists.newArrayList();
        if (source == Const.InputRole.USER) {
            for (InputUser inputUser : inputUsers) {
                InputInfoVO inputInfoVO = new InputInfoVO();
                BeanUtils.copyProperties(inputUser, inputInfoVO);
                inputInfoVO.setPersonOrEnterpriseName("个人");
                inputInfoVOS.add(inputInfoVO);
            }
        } else {
            for (InputEnterprise inputEnterpris : inputEnterprises) {
                InputInfoVO inputInfoVO = new InputInfoVO();
                BeanUtils.copyProperties(inputEnterpris, inputInfoVO);
                Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(inputEnterpris.getEnterpriseId());
                inputInfoVO.setPersonOrEnterpriseName(enterprise.getName());
                inputInfoVOS.add(inputInfoVO);
            }
        }
        return ServerResponse.createBySuccess(inputInfoVOS);
    }


    @Override
    public ServerResponse<List> stockRemind(int sourceId, int source, int type, int threshold) {
        //1.判断来源 0为用户，1为企业
        //2.判断数量与保质期

        if (source == 0) {
            List<InputUser> inputUsers = Lists.newArrayList();
            if (type == 0) {
                //判断数量
                inputUsers = inputUserMapper.getStockByQuantity(sourceId, threshold);
            } else {
                //判断保质期
                Date today = new Date();
                List<InputUser> inputUserList = inputUserMapper.selectAll(sourceId);
                for (InputUser inputUser : inputUserList) {
                    if (DateUtil.getDateUtil().
                            getDistanceTime(inputUser.getProductionTime(), today)
                            > inputUser.getShelfLife()) {
                        inputUsers.add(inputUser);
                    }
                }
            }
            return ServerResponse.createBySuccess(inputUsers);
        } else {
            List<InputEnterprise> enterprises = Lists.newArrayList();
            if (type == 0) {
                //判断数量
                enterprises = inputEnterpriseMapper.getStockByQuantity(sourceId, threshold);
            } else {
                //判断保质期
                Date today = new Date();
                List<InputEnterprise> inputEnterpriseList = inputEnterpriseMapper.selectAll(sourceId);
                for (InputEnterprise inputEnterprise : inputEnterpriseList) {
                    if (DateUtil.getDateUtil().
                            getDistanceTime(inputEnterprise.getProductionTime(), today)
                            > inputEnterprise.getShelfLife()) {
                        enterprises.add(inputEnterprise);
                    }
                }
            }
            return ServerResponse.createBySuccess(enterprises);
        }
    }

    @Override
    public ServerResponse returnInput(int id, float quantity) {
        //1.利用id取回记录，判断数量是否超出
        InputUser inputUser = inputUserMapper.selectByPrimaryKey(id);
        if(inputUser == null){
            return ServerResponse.createByErrorMessage("id无效，记录为空");
        }
        if(inputUser.getSource() == 0){
            return ServerResponse.createByErrorMessage("该投入品不是由企业领用！");
        }
        double result = MathUtil.sub(inputUser.getQuantity(), quantity);
        if(result < 0){
            return ServerResponse.createByErrorMessage("数量超过存在额!");
        }
        //2.对数量进行删减，存入数据库
        inputUser.setQuantity((float) result);
        inputUserMapper.updateByPrimaryKeySelective(inputUser);
        InputEnterprise inputEnterprise = inputEnterpriseMapper.selectByPrimaryKey(inputUser.getSourceId());
        float resultE = (float)MathUtil.add(inputEnterprise.getQuantity(), quantity);
        inputEnterprise.setQuantity(resultE);
        inputEnterpriseMapper.updateByPrimaryKeySelective(inputEnterprise);
        //3.更新退回表
        InputReturn inputReturn = new InputReturn(inputUser.getUserId(), inputEnterprise.getId(), quantity);
        inputReturnMapper.insert(inputReturn);
        return ServerResponse.createBySuccess();
    }

    public static void main(String[] args) {
        /*String orderBy = "c_time_desc";
        String[] orderByArray = orderBy.split("_");
        orderByArray = new String[]{orderByArray[0] + "_" + orderByArray[1], orderByArray[2]};
        System.out.println();*/

    }
}
