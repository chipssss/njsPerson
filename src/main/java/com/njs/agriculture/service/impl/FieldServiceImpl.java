package com.njs.agriculture.service.impl;

import com.google.common.collect.Lists;
import com.njs.agriculture.VO.FieldInfoVO;
import com.njs.agriculture.VO.FieldVO;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.mapper.CropInfoMapper;
import com.njs.agriculture.mapper.EnterpriseMapper;
import com.njs.agriculture.mapper.FieldMapper;
import com.njs.agriculture.pojo.CropInfo;
import com.njs.agriculture.pojo.Enterprise;
import com.njs.agriculture.pojo.Field;
import com.njs.agriculture.pojo.UserRelationship;
import com.njs.agriculture.service.IFieldService;
import com.njs.agriculture.service.IFileService;
import com.njs.agriculture.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.dc.pr.PRError;

import java.util.List;
import java.util.Map;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/30
 * @Description:
 */

@Service("iFieldService")
@Slf4j
public class FieldServiceImpl implements IFieldService {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private FieldMapper fieldMapper;

    @Autowired
    private CropInfoMapper cropInfoMapper;

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Override
    public ServerResponse addField(FieldVO fieldVO) {
        Field field = fieldVO2Field(fieldVO);
        if (field == null) {
            return ServerResponse.createByErrorMessage("参数出错！");
        }
        int resultCount = fieldMapper.insert(field);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("插入田块信息失败！");
        }
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse delField(int fieldId) {
        int resultCount = fieldMapper.deleteByPrimaryKey(fieldId);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("删除田块失败！");
        }
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse modifyField(FieldVO fieldVO) {
        Field field = fieldVO2Field(fieldVO);
        if (field == null) {
            return ServerResponse.createByErrorMessage("参数出错！");
        }
        int resultCount = fieldMapper.updateByPrimaryKeySelective(field);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("更新田块失败！");
        }
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse fieldInfo(int userId) {
        //1.先判断是否负责人
        ServerResponse<Map> serverResponse = iUserService.isManager(userId);
        List<Field> fields = Lists.newArrayList();
        //用户id查询
        fields = fieldMapper.selectBySourceId((int) serverResponse.getData().get("source"), (int) serverResponse.getData().get("sourceId"));

        return ServerResponse.createBySuccess(fieldList2FieldInfoVOList(fields));
    }


    private List<FieldInfoVO> fieldList2FieldInfoVOList(List<Field> fieldList){
        List<FieldInfoVO> fieldInfoVOList = Lists.newLinkedList();
        for (Field field : fieldList) {
            FieldInfoVO fieldInfoVO = new FieldInfoVO();
            BeanUtils.copyProperties(field, fieldInfoVO);
            if(field.getSource() == 1){
                Enterprise enterprise  = enterpriseMapper.selectByPrimaryKey(field.getSourceId());
                if(enterprise != null){
                    fieldInfoVO.setSourceName(enterprise.getName());
                }
            }else {
                fieldInfoVO.setSourceName("个人");
            }
            fieldInfoVOList.add(fieldInfoVO);
        }
        return fieldInfoVOList;
    }

    private Field fieldVO2Field(FieldVO fieldVO) {
        Field field = new Field();
        BeanUtils.copyProperties(fieldVO, field);
        if (fieldVO.isPerson()) {
            field.setSource(0);
            field.setSourceId(fieldVO.getUserId());
        } else {
            ServerResponse<Map> serverResponse = iUserService.isManager(fieldVO.getUserId());
            if ((int)serverResponse.getData().get("source") == 0) {
                return null;
            }
            field.setSource(1);
            field.setSourceId((int)serverResponse.getData().get("sourceId"));
        }
        if (fieldVO.isFree()) {
            field.setStatus(0);
        } else {
            field.setStatus(1);
        }
        field.setCropId(fieldVO.getCropId());
        CropInfo cropInfo = cropInfoMapper.selectByPrimaryKey(fieldVO.getCropId());
        if (cropInfo != null) {
            field.setCropName(cropInfo.getName());
        }
        return field;
    }
}

