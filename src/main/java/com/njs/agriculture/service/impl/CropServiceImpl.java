package com.njs.agriculture.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.njs.agriculture.VO.CropInfoVO;
import com.njs.agriculture.VO.CropSecondCateVO;
import com.njs.agriculture.VO.CropThirdCateVO;
import com.njs.agriculture.VO.CropCateVO;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.mapper.CropFirstCateMapper;
import com.njs.agriculture.mapper.CropInfoMapper;
import com.njs.agriculture.mapper.CropSecondCateMapper;
import com.njs.agriculture.mapper.CropThirdCateMapper;
import com.njs.agriculture.pojo.*;
import com.njs.agriculture.service.ICropService;
import com.njs.agriculture.service.IUserService;
import net.sf.jsqlparser.schema.Server;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/8/1
 * @Description:
 */
@Service("iCropSerivce")
public class CropServiceImpl implements ICropService {

    @Autowired
    IUserService iUserService;

    @Autowired
    CropInfoMapper cropInfoMapper;

    @Autowired
    CropFirstCateMapper cropFirstCateMapper;

    @Autowired
    CropSecondCateMapper cropSecondCateMapper;

    @Autowired
    CropThirdCateMapper cropThirdCateMapper;

    @Override
    public ServerResponse cropAdd(int userId, String name, int typeId) {
        ServerResponse<UserRelationship> response =  iUserService.isManager(userId);
        int resultRow;
        CropInfo cropInfo;
        if(response.isSuccess()){
            //企业
            cropInfo = new CropInfo(typeId, name, response.getData().getEnterpriseId(), 1);
            resultRow = cropInfoMapper.insert(cropInfo);
        }else {
            cropInfo = new CropInfo(typeId, name, response.getData().getEnterpriseId(), 0);
            resultRow = cropInfoMapper.insert(cropInfo);
        }
        if(resultRow == 0){
            return ServerResponse.createByErrorMessage("添加失败！");
        }
        return ServerResponse.createBySuccess(cropInfo);
    }

    @Override
    public ServerResponse cropGet(int pageNum, int pageSize) {
        /*List<CropCateVO> cropCateVOList = Lists.newLinkedList();
        PageHelper.startPage(pageNum, pageSize);
        List<CropFirstCate> firstCateList = cropFirstCateMapper.selectAll();
        for (CropFirstCate cropFirstCate : firstCateList) {
            CropCateVO cropCateVO = new CropCateVO();
            BeanUtils.copyProperties(cropFirstCate, cropCateVO);

            List<CropSecondCateVO> cropSecondCateVOS = Lists.newLinkedList();
            List<CropSecondCate> secondCateList = cropSecondCateMapper.selectByFirstCateId(cropFirstCate.getId());
            for (CropSecondCate cropSecondCate : secondCateList) {
                CropSecondCateVO cropSecondCateVO = new CropSecondCateVO();
                BeanUtils.copyProperties(cropSecondCate,cropSecondCateVO);

                List<CropThirdCateVO> cropThirdCateVOS = Lists.newLinkedList();
                List<CropThirdCate> thirdCateList = cropThirdCateMapper.selectBySecondCateId(cropSecondCate.getId());
                for (CropThirdCate cropThirdCate : thirdCateList) {
                    CropThirdCateVO cropThirdCateVO = new CropThirdCateVO();
                    BeanUtils.copyProperties(cropThirdCate,cropThirdCateVO);

                    List<CropInfo> cropInfoList = cropInfoMapper.selectByCateId(cropThirdCate.getId());
                    cropThirdCateVO.setCropInfoList(cropInfoList);
                    cropThirdCateVOS.add(cropThirdCateVO);
                }
                cropSecondCateVO.setThirdCateList(cropThirdCateVOS);
                cropSecondCateVOS.add(cropSecondCateVO);

            }
            cropCateVO.setSecondCateList(cropSecondCateVOS);
            cropCateVOList.add(cropCateVO);
        }
        return ServerResponse.createBySuccess(cropCateVOList);*/
        PageHelper.startPage(pageNum, pageSize);
        List<CropInfo> cropInfoList = cropInfoMapper.selectAll();
        List<CropInfoVO> cropInfoVOList = Lists.newLinkedList();
        for (CropInfo cropInfo : cropInfoList) {
            CropInfoVO infoVO = new CropInfoVO();
            infoVO.setCropInfoId(cropInfo.getId());
            infoVO.setCropInfoName(cropInfo.getName());
            CropThirdCate cropThirdCate = cropThirdCateMapper.selectByPrimaryKey(cropInfo.getCategoryId());
            infoVO.setThirdCateId(cropThirdCate.getId());
            infoVO.setThirdCateName(cropThirdCate.getName());
            CropSecondCate cropSecondCate = cropSecondCateMapper.selectByPrimaryKey(cropThirdCate.getSecondcateId());
            infoVO.setSecondCateId(cropSecondCate.getId());
            infoVO.setSecondCateName(cropSecondCate.getName());
            CropFirstCate cropFirstCate = cropFirstCateMapper.selectByPrimaryKey(cropSecondCate.getFirstcateId());
            infoVO.setFirstCateId(cropFirstCate.getId());
            infoVO.setFirstCateName(cropFirstCate.getName());
            cropInfoVOList.add(infoVO);
        }
        return ServerResponse.createBySuccess(cropInfoVOList);
    }

    @Override
    public ServerResponse cropDel(int id, int flag) {
        int resultRow = 0;
        if(flag == 1){
            resultRow = cropFirstCateMapper.deleteByPrimaryKey(id);
        }else if(flag == 2){
            resultRow = cropSecondCateMapper.deleteByPrimaryKey(id);
        }else if(flag == 3){
            resultRow = cropThirdCateMapper.deleteByPrimaryKey(id);
        }else {
            resultRow = cropInfoMapper.deleteByPrimaryKey(id);
        }
       if(resultRow == 0){
           return ServerResponse.createByErrorMessage("删除失败！");
       }
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse cropSecondCateGet() {
        return ServerResponse.createBySuccess(cropSecondCateMapper.selectAll());
    }

    @Override
    public ServerResponse cropFirstCateGet() {
        return ServerResponse.createBySuccess(cropFirstCateMapper.selectAll());
    }

    @Override
    public ServerResponse cropThirdCateGet() {
        return ServerResponse.createBySuccess(cropThirdCateMapper.selectAll());
    }
}
