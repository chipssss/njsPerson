package com.njs.agriculture.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
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
        List<CropCateVO> cropCateVOList = Lists.newLinkedList();
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
        return ServerResponse.createBySuccess(cropCateVOList);
    }
}
