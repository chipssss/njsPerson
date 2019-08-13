package com.njs.agriculture.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.njs.agriculture.VO.ServicePoolVO;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.mapper.*;
import com.njs.agriculture.pojo.ServiceInfo;
import com.njs.agriculture.pojo.ServicePool;
import com.njs.agriculture.pojo.UserRelationship;
import com.njs.agriculture.service.IServiceService;
import com.njs.agriculture.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/8/3
 * @Description:
 */
@Service("iServiceService")
public class ServiceServiceIml implements IServiceService {

    @Autowired
    private ServicePoolMapper servicePoolMapper;

    @Autowired
    private ServiceInfoMapper serviceInfoMapper;

    @Autowired
    private UserRelationshipMapper userRelationshipMapper;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Override
    public ServerResponse serviceApply(ServicePool servicePool) {
        servicePool.setStatus(0);
        int sourceId = servicePool.getSourceId();
        ServerResponse<Map> serverResponse = iUserService.isManager(sourceId);

        servicePool.setSource((int) serverResponse.getData().get("source"));
        servicePool.setSourceId((int) serverResponse.getData().get("sourceId"));

        if (servicePoolMapper.validExist(servicePool.getServiceId(),
                servicePool.getSourceId(), servicePool.getSource()) != 0) {
            return ServerResponse.createByErrorMessage("重复申请！");
        }
        int resultRow = servicePoolMapper.insert(servicePool);
        if (resultRow == 0) {
            return ServerResponse.createByErrorMessage("申请失败！");
        }
        return ServerResponse.createBySuccess(servicePool.getId());
    }

    @Override
    public ServerResponse serviceListGet() {
        return ServerResponse.createBySuccess(serviceInfoMapper.selectAll());
    }

    @Override
    public ServerResponse serviceInfoGet(int flag, int sourceId) {
        List<Integer> serviceByEnterprise = Lists.newLinkedList();
        Set<Integer> serviceIds = new HashSet<>();
        List<Integer> enterpriseIds = Lists.newLinkedList();
        if (flag == 1) {
            //查的是企业
            enterpriseIds.add(sourceId);
            serviceByEnterprise = servicePoolMapper.selectServiceIdsByEnterprieIds(enterpriseIds);
        } else {
            //查的是用户
            List<UserRelationship> relationships = userRelationshipMapper.selectByUserId(sourceId);

            for (UserRelationship relationship : relationships) {
                enterpriseIds.add(relationship.getEnterpriseId());
            }
            if (!enterpriseIds.isEmpty()) {
                serviceByEnterprise = servicePoolMapper.selectServiceIdsByEnterprieIds(enterpriseIds);
            }
            //用户，key
            List<Integer> serviceByUser = servicePoolMapper.selectServiceIdsByUserId(sourceId);
            if (!serviceByUser.isEmpty()) {
                serviceIds.addAll(serviceByUser);
            }
        }
        if (!serviceByEnterprise.isEmpty()) {
            serviceIds.addAll(serviceByEnterprise);
        }
        List<Integer> serviceIdList = new ArrayList<>(serviceIds);
        List<ServiceInfo> ownList = serviceInfoMapper.selectOwn(serviceIdList);
        List<ServiceInfo> notOwnList = serviceInfoMapper.selectNotOwn(serviceIdList);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ownList", ownList);
        jsonObject.put("notOwnList", notOwnList);
        return ServerResponse.createBySuccess(jsonObject);
    }

    @Override
    public ServerResponse serviceApplyRecord(int userId) {
        ServerResponse<Map> serverResponse = iUserService.isManager(userId);
        List<ServicePool> servicePoolList;
        PageHelper.orderBy("source");
        servicePoolList = servicePoolMapper.
                selectByUserId((int) serverResponse.getData().get("sourceId"), (int) serverResponse.getData().get("source"));
        return ServerResponse.createBySuccess(pool2VO(servicePoolList));
    }

    @Override
    public ServerResponse applyerGet(int status) {
        PageHelper.orderBy("source");
        List<ServicePool> servicePoolList = servicePoolMapper.selectByStatus(status);
        return ServerResponse.createBySuccess(pool2VO(servicePoolList));
    }

    @Override
    public ServerResponse serviceUpdate(int id, int status, String reply) {
        ServicePool servicePool = new ServicePool();
        servicePool.setId(id);
        servicePool.setStatus(status);
        servicePool.setReply(reply);
        int resultRow = servicePoolMapper.updateByPrimaryKeySelective(servicePool);
        if (resultRow == 0) {
            return ServerResponse.createByErrorMessage("更新失败！");
        }
        return ServerResponse.createBySuccess();
    }

    private List<ServicePoolVO> pool2VO(List<ServicePool> servicePoolList) {
        List<ServicePoolVO> servicePoolVOList = Lists.newLinkedList();
        for (ServicePool servicePool : servicePoolList) {
            ServicePoolVO servicePoolVO = new ServicePoolVO();
            BeanUtils.copyProperties(servicePool, servicePoolVO);
            if (servicePool.getSource() == 0) {
                servicePoolVO.setName("个人:" + userMapper.selectByPrimaryKey(servicePool.getSourceId()).getUsername());
            } else {
                servicePoolVO.setName("企业:" + enterpriseMapper.selectByPrimaryKey(servicePool.getSourceId()).getName());
            }
            servicePoolVOList.add(servicePoolVO);
        }
        return servicePoolVOList;
    }
}
