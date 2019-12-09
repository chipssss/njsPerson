package com.njs.agriculture.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.njs.agriculture.VO.*;
import com.njs.agriculture.common.Const;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.dto.InputDTO;
import com.njs.agriculture.mapper.*;
import com.njs.agriculture.pojo.*;
import com.njs.agriculture.service.IInputService;
import com.njs.agriculture.service.IUserService;
import com.njs.agriculture.utils.DateUtil;
import com.njs.agriculture.utils.HttpsUtil;
import com.njs.agriculture.utils.MathUtil;
import net.sf.jsqlparser.schema.Server;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/25
 * @Description:
 */
@Service("iInputservice")
public class InputServiceImpl<T> implements IInputService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    private IUserService iUserService;

    @Autowired
    FieldMapper fieldMapper;

    @Autowired
    CropInfoMapper cropInfoMapper;

    @Autowired
    private InputReturnMapper inputReturnMapper;

    @Autowired
    EnterpriseMapper enterpriseMapper;

    @Autowired
    InputFirstCateMapper inputFirstCateMapper;

    @Autowired
    InputSecondCateMapper inputSecondCateMapper;

    @Autowired
    InputThirdCateMapper inputThirdCateMapper;

    @Autowired
    InputUserMapper inputUserMapper;

    @Autowired
    InputEnterpriseMapper inputEnterpriseMapper;

    @Autowired
    InputPurchaseMapper inputPurchaseMapper;

    @Autowired
    InputConsumeMapper inputConsumeMapper;

    @Autowired
    InputBarcodeMapper inputBarcodeMapper;

    @Autowired
    InputUsedMapper inputUsedMapper;

    @Autowired
    InputDTOMapper inputDTOMapper;

    @Autowired
    InputStreamMapper inputStreamMapper;


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
                return ServerResponse.createByErrorMessage("农资录入失败！");
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
    public ServerResponse categoryInfo(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<InputDTO> inputDTOList = inputDTOMapper.selectAll();
        PageInfo pageResult = new PageInfo(inputDTOList);
        //3.构造类别对象
        return ServerResponse.createBySuccess(pageResult);
    }

    @Override
    public ServerResponse categoryInfoForAndroid() {
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
        return ServerResponse.createBySuccess(inputCategoryVOS);
    }

    @Override
    public ServerResponse firstCateGet() {
        return ServerResponse.createBySuccess(inputFirstCateMapper.selectAll());
    }

    @Override
    public ServerResponse secondCateGet() {
        return ServerResponse.createBySuccess(inputSecondCateMapper.selectAll());
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

    @Override
    public  ServerResponse thirdCateSet(InputThirdCate inputThirdCate){
       inputThirdCateMapper.insert(inputThirdCate);
      if(inputThirdCate.getId()!=0){
          Map data=new HashMap();
          data.put("id",inputThirdCate.getId());
          data.put("secondCateId",inputThirdCate.getSecondcateId());
          data.put("name",inputThirdCate.getName());
          return ServerResponse.createBySuccess(data);
      }

        return ServerResponse.createByErrorMessage("添加失败");
    }
    @Override
    public ServerResponse thirdCateGet(Integer firstCateId,Integer secondCateId){
        List<InputThirdCate> inputThirdCateList;
        if(firstCateId==0||firstCateId==null||secondCateId==0||secondCateId==null){
            inputThirdCateList=inputThirdCateMapper.selectAll();
        }
        else{
            inputThirdCateList=inputThirdCateMapper.selectBySecondCateId(secondCateId);
        }

        Map data =new HashMap();
        if(inputThirdCateList==null||inputThirdCateList.size()==0){
            data.put("firstCateId",firstCateId);
            data.put("secondCateId",secondCateId);
            return  ServerResponse.createByError("没有相关的第三级类别",data);
        }
        else {

            return ServerResponse.createBySuccess(inputThirdCateList);
        }
    }

    @Override
    public ServerResponse sumGet(int source, int sourceId) {
        int totalEntries;
        double totalInputs = 0;
        double totalAmount = 0;
        if (source == 0) {
            List<InputUser> inputUsers = inputUserMapper.selectAll(sourceId);
            totalEntries = inputUsers.size();
            for (InputUser inputUser : inputUsers) {
                totalInputs = MathUtil.add(String.valueOf(totalInputs), inputUser.getQuantity().toString());
                double amount = MathUtil.mul(inputUser.getQuantity().toString(), inputUser.getPrice().toString());
                totalAmount = MathUtil.add(String.valueOf(totalAmount), String.valueOf(amount));
            }
        } else {
            List<InputEnterprise> inputEnterprises = inputEnterpriseMapper.selectAll(sourceId);
            totalEntries = inputEnterprises.size();
            for (InputEnterprise inputEnterprise : inputEnterprises) {
                totalInputs = MathUtil.add(String.valueOf(totalInputs), inputEnterprise.getQuantity().toString());
                double amount = MathUtil.mul(inputEnterprise.getQuantity().toString(), inputEnterprise.getPrice().toString());
                totalAmount = MathUtil.add(String.valueOf(totalAmount), String.valueOf(amount));
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("totalEntries", totalEntries);
        jsonObject.put("totalInputs", totalInputs);
        jsonObject.put("totalAmount", totalAmount);
        return ServerResponse.createBySuccess(jsonObject);
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
        if (inputUser == null) {
            return ServerResponse.createByErrorMessage("id无效，记录为空");
        }
        if (inputUser.getSource() == 0) {
            return ServerResponse.createByErrorMessage("该投入品不是由企业领用！");
        }
        double result = MathUtil.sub(inputUser.getQuantity().toString(), String.valueOf(quantity));
        if (result < 0) {
            return ServerResponse.createByErrorMessage("数量超过存在额!");
        }
        //2.对数量进行删减，存入数据库
        inputUser.setQuantity((float) result);
        inputUserMapper.updateByPrimaryKeySelective(inputUser);
        InputEnterprise inputEnterprise = inputEnterpriseMapper.selectByPrimaryKey(inputUser.getSourceId());
        float resultE = (float) MathUtil.add(inputEnterprise.getQuantity().toString(), String.valueOf(quantity));
        inputEnterprise.setQuantity(resultE);
        inputEnterpriseMapper.updateByPrimaryKeySelective(inputEnterprise);
        //3.更新退回表
        InputReturn inputReturn = new InputReturn(inputUser.getUserId(), inputEnterprise.getId(), quantity);
        inputReturnMapper.insert(inputReturn);
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse scanBarcode(String barCode) {
        InputBarcode inputBarcode = inputBarcodeMapper.selectByBarCode(barCode);
        if (inputBarcode != null) {
            return ServerResponse.createBySuccess(inputBarcode);
        }
        InputBarcode inputBarcode1 = HttpsUtil.Get(barCode);
        if (inputBarcode1 == null) {
            return ServerResponse.createByErrorMessage("查询不到记录!");
        }
        inputBarcodeMapper.insert(inputBarcode1);
        return ServerResponse.createBySuccess(inputBarcode1);
    }

    @Override
    public ServerResponse inputRecord(int source, int sourceId, int type) {
        // TODO 解决重复代码
        List<InputRecordVO> recordVOList = Lists.newLinkedList();
        //1.先判断type，购入/领用/退回/使用 为 1/2/3/4
        if (type == 1) {
            List<InputPurchase> purchaseList = inputPurchaseMapper.selectBySource(source, sourceId);
            for (InputPurchase inputPurchase : purchaseList) {
                InputRecordVO inputRecordVO;
                //企业
                if (source == 1) {
                    InputEnterprise inputEnterprise = inputEnterpriseMapper.selectByPrimaryKey(inputPurchase.getSourceId());
                    String enterpriseName = enterpriseMapper.selectByPrimaryKey(inputEnterprise.getEnterpriseId()).getName();
                    inputRecordVO = new InputRecordVO(inputPurchase.getSourceId(), inputEnterprise.getName(),
                            inputPurchase.getQuantity(), inputPurchase.getCreateTime(), enterpriseName);
                } else {
                    InputUser inputUser = inputUserMapper.selectByPrimaryKey(inputPurchase.getSourceId());
                    inputRecordVO = new InputRecordVO(inputPurchase.getSourceId(), inputUser.getName(),
                            inputPurchase.getQuantity(), inputPurchase.getCreateTime(), Const.PERSONNAL);
                }
                recordVOList.add(inputRecordVO);
            }
        } else if (type == 2) {
            //领用
            List<InputConsume> consumeList;
            if (source == 1) {
                consumeList = inputConsumeMapper.selectByEnterpriseId(sourceId);
            } else {
                consumeList = inputConsumeMapper.selectByUserId(sourceId);
            }
            for (InputConsume inputConsume : consumeList) {
                InputRecordVO inputRecordVO;
                InputEnterprise inputEnterprise = inputEnterpriseMapper.selectByPrimaryKey(inputConsume.getEnterpriseinputId());
                String enterpriseName = enterpriseMapper.selectByPrimaryKey(inputEnterprise.getEnterpriseId()).getName();
                inputRecordVO = new InputRecordVO(inputConsume.getEnterpriseinputId(), inputEnterprise.getName(),
                        inputConsume.getQuantity(), inputConsume.getCreateTime(), enterpriseName);
                recordVOList.add(inputRecordVO);
            }
        } else if (type == 3) {
            List<InputReturn> returnList;
            if (source == 1) {
                returnList = inputReturnMapper.selectByEnterpriseId(sourceId);
            } else {
                returnList = inputReturnMapper.selectByUserId(sourceId);
            }
            for (InputReturn inputReturn : returnList) {
                InputRecordVO inputRecordVO;
                InputEnterprise inputEnterprise = inputEnterpriseMapper.selectByPrimaryKey(inputReturn.getEnterpriseinputId());
                Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(inputEnterprise.getEnterpriseId());
                inputRecordVO = new InputRecordVO(inputReturn.getEnterpriseinputId(), inputEnterprise.getName(),
                        inputReturn.getQuantity(), inputReturn.getCreateTime(), enterprise.getName());
                recordVOList.add(inputRecordVO);
            }
        } else if (type == 4) {
            List<InputUsed> usedList = inputUsedMapper.selectBySource(source, sourceId);
            for (InputUsed inputUsed : usedList) {
                InputRecordVO inputRecordVO;
                if (source == 1) {
                    InputEnterprise inputEnterprise = inputEnterpriseMapper.selectByPrimaryKey(inputUsed.getSourceId());
                    String enterpriseName = enterpriseMapper.selectByPrimaryKey(inputEnterprise.getEnterpriseId()).getName();
                    inputRecordVO = new InputRecordVO(inputUsed.getSourceId(), inputEnterprise.getName(),
                            inputUsed.getQuantity(), inputUsed.getCreateTime(), enterpriseName);
                } else {
                    InputUser inputUser = inputUserMapper.selectByPrimaryKey(inputUsed.getSourceId());
                    inputRecordVO = new InputRecordVO(inputUsed.getSourceId(), inputUser.getName(),
                            inputUsed.getQuantity(), inputUsed.getCreateTime(), Const.PERSONNAL);
                }
                recordVOList.add(inputRecordVO);
            }
        }
        return ServerResponse.createBySuccess(recordVOList);
    }

    @Override
    public ServerResponse inputDel(int id, int flag, int source) {
        int resultRow = 0;
        if (flag == 1) {
            resultRow = inputFirstCateMapper.deleteByPrimaryKey(id);
        } else if (flag == 2) {
            resultRow = inputSecondCateMapper.deleteByPrimaryKey(id);
        } else {
            if (source == 1) {
                resultRow = inputEnterpriseMapper.deleteByPrimaryKey(id);
            } else {
                resultRow = inputUserMapper.deleteByPrimaryKey(id);
            }
        }
        if (resultRow == 0) {
            return ServerResponse.createByErrorMessage("删除失败！");
        }
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse inputCateAdd(int type, String name, int superiorId) {
        int resultRow = 0;
        int id = 0;
        if (type == 1) {
            InputFirstCate inputFirstCate = new InputFirstCate();
            inputFirstCate.setName(name);
            resultRow = inputFirstCateMapper.insert(inputFirstCate);
            return ServerResponse.createByResultRow(resultRow, inputFirstCate);
        } else if (type == 2) {
            InputSecondCate inputSecondCate = new InputSecondCate();
            inputSecondCate.setFirstcateId(superiorId);
            inputSecondCate.setName(name);
            resultRow = inputSecondCateMapper.insert(inputSecondCate);
            return ServerResponse.createByResultRow(resultRow, inputSecondCate);
        }
        return ServerResponse.createByErrorMessage("插入失败！");

    }

    @Override
    public ServerResponse inputConsume(InputConsume inputConsume, int userId) {
        inputConsume.setStatus(0);
        inputConsume.setUserId(userId);
        //3.插入信息.
        int resultRow = inputConsumeMapper.insert(inputConsume);
        return ServerResponse.createByResultRow(resultRow, inputConsume);
    }

    @Override
    public ServerResponse inputConsumeList(int userId) {
        Map map = iUserService.isManager(userId).getData();
        if(map.isEmpty() || (int)map.get("source") == 0){
            return ServerResponse.createByErrorMessage("没有权限！");
        }
        List<InputConsume> inputConsumeList = inputConsumeMapper.selectByStatus(0, (int)map.get("sourceId"));
        return ServerResponse.createBySuccess(consume2ConsumeVO(inputConsumeList));
    }

    @Override
    @Transactional
    public ServerResponse inputConsumeReview(int id, int status, int userId) {
        Map map = iUserService.isManager(userId).getData();
        if(map.isEmpty() || (int)map.get("source") == 0){
            return ServerResponse.createByErrorMessage("没有权限！");
        }
        InputConsume inputConsume = inputConsumeMapper.selectByPrimaryKey(id);
        if(status == 1){
            //1.判断企业库够不够扣，够就直接扣
            InputEnterprise inputEnterprise = inputEnterpriseMapper.selectByPrimaryKey(inputConsume.getEnterpriseinputId());
            if(inputEnterprise == null){
                return ServerResponse.createByErrorMessage("没有该行企业库存记录");
            }
            double result = MathUtil.sub(inputEnterprise.getQuantity().toString(), inputConsume.getQuantity().toString());
            if(result >= 0){
                inputEnterprise.setQuantity(Float.valueOf(String.valueOf(result)));
                inputEnterpriseMapper.updateByPrimaryKeySelective(inputEnterprise);
                if(result == 0){
                    inputEnterpriseMapper.deleteByPrimaryKey(inputEnterprise.getId());
                }
                //2.加入到个人库
                InputUser inputUser = new InputUser();
                BeanUtils.copyProperties(inputEnterprise, inputUser);
                inputUser.setCreateTime(new Date());
                inputUser.setUserId(userId);
                inputUser.setSourceId(inputConsume.getEnterpriseinputId());
                inputUser.setSource(1);
                inputUser.setId(0);
                inputUserMapper.insert(inputUser);
                inputConsume.setUserinputId(inputUser.getId());
            }else{
                return ServerResponse.createByErrorMessage("企业库存不够！");
            }
        }
        inputConsume.setStatus(status);
        int resultRow = inputConsumeMapper.updateByPrimaryKeySelective(inputConsume);
        return ServerResponse.createByResultRow(resultRow);
    }

    @Override
    public ServerResponse inputStreamAdd(int fieldId, int cropId, List<ProcessRecordInfoVO.Input> inputList, int userId, int recordId) {
        Map map = iUserService.isManager(userId).getData();
        InputStream inputStream = new InputStream();
        inputStream.setFieldId(fieldId);
        inputStream.setCropId(cropId);
        inputStream.setSource((int)map.get("source"));
        inputStream.setSourceId((int)map.get("sourceId"));
        inputStream.setRecordId(recordId);
        for (ProcessRecordInfoVO.Input input : inputList) {

            if(input.getQuantity() == 0){
                StringBuilder inputName=new StringBuilder();
               inputName.append(input.getName());
                inputStream.setInputName(inputName.toString());
            }else{
                StringBuilder inputName=new StringBuilder();
                inputName.append(input.getName());
                inputName.append(input.getQuantity());
                inputName.append("kg");
                inputStream.setInputName(inputName.toString());
            }
            inputStream.setQuantity(Double.valueOf(input.getQuantity()).intValue());
            int resultRow = inputStreamMapper.insert(inputStream);
            if(resultRow == 0){
                return ServerResponse.createByError();
            }
        }
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse inputStreamGet(int userId) {
        Map map = iUserService.isManager(userId).getData();
        List<InputStream> inputStreamList = inputStreamMapper.selectBySource((int)map.get("source"), (int)map.get("sourceId"));
        return ServerResponse.createBySuccess(stream2streamVO(inputStreamList));
    }

    private List<InputStreamVO> stream2streamVO(List<InputStream> inputStreamList){
        List<InputStreamVO> inputStreamVOList = Lists.newLinkedList();
        for (InputStream inputStream : inputStreamList) {
            InputStreamVO inputStreamVO = new InputStreamVO();
            BeanUtils.copyProperties(inputStream, inputStreamVO);
            Field field = fieldMapper.selectByPrimaryKey(inputStream.getFieldId());
            inputStreamVO.setFieldName(field.getName());
            inputStreamVO.setCropName(field.getCropName());
            inputStreamVOList.add(inputStreamVO);
        }
        return inputStreamVOList;
    }

    private List<InputConsumeVO> consume2ConsumeVO(List<InputConsume> inputConsumeList){
        List<InputConsumeVO> inputConsumeVOList = Lists.newLinkedList();
        for (InputConsume inputConsume : inputConsumeList) {
            InputConsumeVO inputConsumeVO = new InputConsumeVO();
            BeanUtils.copyProperties(inputConsume, inputConsumeVO);
            User user = userMapper.selectByPrimaryKey(inputConsume.getUserId());
            inputConsumeVO.setUserName(user.getUsername());
            InputEnterprise inputEnterprise = inputEnterpriseMapper.selectByPrimaryKey(inputConsume.getEnterpriseinputId());
            inputConsumeVO.setInputName(inputEnterprise.getName());
            inputConsumeVOList.add(inputConsumeVO);
        }
        return inputConsumeVOList;
    }

}
