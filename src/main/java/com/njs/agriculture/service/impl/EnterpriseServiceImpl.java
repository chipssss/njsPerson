package com.njs.agriculture.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.njs.agriculture.VO.PersonnelVO;
import com.njs.agriculture.common.Const;
import com.njs.agriculture.common.ResponseCode;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.mapper.EnterpriseMapper;
import com.njs.agriculture.mapper.UserMapper;
import com.njs.agriculture.mapper.UserRelationshipMapper;
import com.njs.agriculture.pojo.Enterprise;
import com.njs.agriculture.pojo.User;
import com.njs.agriculture.pojo.UserRelationship;
import com.njs.agriculture.service.IEnterpriseService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/8/2
 * @Description:
 */
@Service("iEnterpriseService")
public class EnterpriseServiceImpl implements IEnterpriseService {

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private UserRelationshipMapper userRelationshipMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse enterpriseAdd(Enterprise enterprise) {
        enterprise.setStatus(0);
        int resultRow = enterpriseMapper.insert(enterprise);
        if(resultRow == 0){
            return ServerResponse.createByErrorMessage("开设组织插入表失败！");
        }
        return ServerResponse.createBySuccess(enterprise);
    }

    @Override
    public ServerResponse enterpriseGet(int status, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        if(status == 0 || status == 1){
            List<Enterprise> enterprises = enterpriseMapper.selectByStatus(status);
            return ServerResponse.createBySuccess(enterprises);
        }else {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),"status参数错误！");
        }

    }

    @Override
    public ServerResponse personnelGet(int enterpriseId) {
        List<UserRelationship> userRelationships = userRelationshipMapper.selectByEnterpriseId(enterpriseId);
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(enterpriseId);
        List<PersonnelVO> personnelVOS = Lists.newLinkedList();
        for (UserRelationship userRelationship : userRelationships) {
            User user = userMapper.selectByPrimaryKey(userRelationship.getUserId());
            PersonnelVO personnelVO = new PersonnelVO();
            int position = userRelationship.getPosition();
            if(position == Const.Position.STAFF){
                personnelVO.setPosition("员工");
            }else if(position == Const.Position.MANAGER){
                personnelVO.setPosition("经理");
            }else if (position == Const.Position.CHARGER){
                personnelVO.setPosition("负责人");
            }else{
                personnelVO.setPosition("未知");
            }
            personnelVO.setCompanyName(enterprise.getName());
            personnelVO.setPhoneNum(user.getPhonenum());
            personnelVO.setStaffName(user.getUsername());
            personnelVOS.add(personnelVO);
        }
        return ServerResponse.createBySuccess(personnelVOS);
    }

    @Override
    public ServerResponse enterpriseExamine(int status, int enterpriseId) {
        Enterprise enterprise = new Enterprise();
        enterprise.setId(enterpriseId);
        enterprise.setStatus(status);
        int resultRow = enterpriseMapper.updateByPrimaryKeySelective(enterprise);
        if(resultRow == 0){
            return ServerResponse.createByErrorMessage("更新失败！");
        }
        return ServerResponse.createBySuccess();
    }
}
