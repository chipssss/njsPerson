package com.njs.agriculture.service.impl;

import com.google.common.collect.Lists;
import com.njs.agriculture.VO.FieldVO;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.mapper.FieldMapper;
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

    @Override
    public ServerResponse addField(FieldVO fieldVO) {
        Field field = new Field();
        BeanUtils.copyProperties(fieldVO, field);
        if(fieldVO.isPerson()){
            field.setSource(0);
            field.setSourceId(fieldVO.getUserId());
        }else {
            ServerResponse<UserRelationship> serverResponse = iUserService.isManager(fieldVO.getUserId());
            if(!serverResponse.isSuccess()){
                return serverResponse;
            }
            field.setSource(1);
            field.setSourceId(serverResponse.getData().getEnterpriseId());
        }
        if(fieldVO.isFree()){
            field.setStatus(0);
        }else {
            field.setStatus(1);
        }
        int resultCount = fieldMapper.insert(field);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("插入田块信息失败！");
        }
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse delField(int fieldId) {
        int resultCount = fieldMapper.deleteByPrimaryKey(fieldId);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("删除田块失败！");
        }
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse modifyField(Field field) {
        int resultCount = fieldMapper.updateByPrimaryKeySelective(field);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("更新田块失败！");
        }
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse fieldInfo(int userId) {
        //1.先判断是否负责人
        ServerResponse<UserRelationship> serverResponse = iUserService.isManager(userId);
        List<Field> fields = Lists.newArrayList();
        if(!serverResponse.isSuccess()){
            //用户id查询
            fields = fieldMapper.selectBySourceId(userId);
        }else{
            fields = fieldMapper.selectBySourceId(serverResponse.getData().getEnterpriseId());
        }
        return ServerResponse.createBySuccess(fields);
    }
}
