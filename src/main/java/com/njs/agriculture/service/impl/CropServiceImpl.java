package com.njs.agriculture.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.njs.agriculture.VO.CropInfoVO;
import com.njs.agriculture.VO.CropSecondCateVO;
import com.njs.agriculture.VO.CropThirdCateVO;
import com.njs.agriculture.VO.CropCateVO;
import com.njs.agriculture.common.ResponseCode;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.dto.CropDTO;
import com.njs.agriculture.mapper.*;
import com.njs.agriculture.pojo.*;
import com.njs.agriculture.service.ICropService;
import com.njs.agriculture.service.IUserService;
import net.sf.jsqlparser.schema.Server;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Autowired
    CropDTOMapper cropDTOMapper;

    @Override
    public ServerResponse cropAdd(int userId, String name, int typeId) {
        ServerResponse<Map> response = iUserService.isManager(userId);
        int resultRow;
        CropInfo cropInfo;
        cropInfo = new CropInfo(typeId, name, (int) response.getData().get("sourceId"), (int) response.getData().get("source"));
        resultRow = cropInfoMapper.insert(cropInfo);
        if (resultRow == 0) {
            return ServerResponse.createByErrorMessage("添加失败！");
        }
        return ServerResponse.createBySuccess(cropInfo);
    }

    @Override
    public ServerResponse cropGet(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CropDTO> cropDTOList = cropDTOMapper.selectAll();
        PageInfo pageResult = new PageInfo(cropDTOList);
        return ServerResponse.createBySuccess(pageResult);
    }

    @Override
    public ServerResponse cropGetAndroid() {
        List<CropCateVO> cropCateVOList = Lists.newLinkedList();
        List<CropFirstCate> firstCateList = cropFirstCateMapper.selectAll();
        for (CropFirstCate cropFirstCate : firstCateList) {
            CropCateVO cropCateVO = new CropCateVO();
            BeanUtils.copyProperties(cropFirstCate, cropCateVO);

            List<CropSecondCateVO> cropSecondCateVOS = Lists.newLinkedList();
            List<CropSecondCate> secondCateList = cropSecondCateMapper.selectByFirstCateId(cropFirstCate.getId());
            for (CropSecondCate cropSecondCate : secondCateList) {
                CropSecondCateVO cropSecondCateVO = new CropSecondCateVO();
                BeanUtils.copyProperties(cropSecondCate, cropSecondCateVO);

                List<CropThirdCate> thirdCateList = cropThirdCateMapper.selectBySecondCateId(cropSecondCate.getId());
                cropSecondCateVO.setThirdCateList(thirdCateList2cropThirdCateVOList(thirdCateList));
                cropSecondCateVOS.add(cropSecondCateVO);

            }
            cropCateVO.setSecondCateList(cropSecondCateVOS);
            cropCateVOList.add(cropCateVO);
        }
        return ServerResponse.createBySuccess(cropCateVOList);
    }

    @Override
    public ServerResponse cropDel(int id, int flag) {
        int resultRow = 0;
        if (flag == 1) {
            resultRow = cropFirstCateMapper.deleteByPrimaryKey(id);
        } else if (flag == 2) {
            resultRow = cropSecondCateMapper.deleteByPrimaryKey(id);
        } else if (flag == 3) {
            resultRow = cropThirdCateMapper.deleteByPrimaryKey(id);
        } else {
            resultRow = cropInfoMapper.deleteByPrimaryKey(id);
        }
        if (resultRow == 0) {
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

    @Override
    public ServerResponse cropCateAdd(int type, String name, int superiorId) {
        int resultRow = 0;
        if(type == 1){
            CropFirstCate cropFirstCate = new CropFirstCate();
            cropFirstCate.setName(name);
            resultRow = cropFirstCateMapper.insert(cropFirstCate);
            return ServerResponse.createByResultRow(resultRow, cropFirstCate);
        }else if(type == 2){
            CropSecondCate secondCate = new CropSecondCate();
            secondCate.setFirstcateId(superiorId);
            secondCate.setName(name);
            resultRow = cropSecondCateMapper.insert(secondCate);
            return ServerResponse.createByResultRow(resultRow, secondCate);
        }else if(type == 3){
            CropThirdCate cropThirdCate = new CropThirdCate();
            CropSecondCate cropSecondCate = cropSecondCateMapper.selectByPrimaryKey(superiorId);
            if(cropSecondCate == null){
                return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "双亲id错误");
            }
            cropThirdCate.setName(name);
            cropThirdCate.setFirstcateId(cropSecondCate.getFirstcateId());
            cropThirdCate.setSecondcateId(superiorId);
            resultRow = cropThirdCateMapper.insert(cropThirdCate);
            return ServerResponse.createByResultRow(resultRow, cropThirdCate);
        }
        return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "入参错误");
    }

    @Override
    public ServerResponse getTea() {
        List<CropThirdCate> thirdCateList = cropThirdCateMapper.selectBySecondCateId(2);
        return ServerResponse.createBySuccess(thirdCateList2cropThirdCateVOList(thirdCateList));
    }

    @Override
    public ServerResponse getIndustrialParkCrop() {
        return getCropByThirdCate(30);
    }

    @Override
    public ServerResponse getCropByThirdCate(int thirdCateId) {
        List<CropInfo> cropInfoList = cropInfoMapper.selectByCateId(thirdCateId);
        return ServerResponse.createBySuccess(cropInfoList);
    }

    public List<CropThirdCateVO> thirdCateList2cropThirdCateVOList(List<CropThirdCate> thirdCateList){
        List<CropThirdCateVO> cropThirdCateVOS = Lists.newLinkedList();
        for (CropThirdCate cropThirdCate : thirdCateList) {
            CropThirdCateVO cropThirdCateVO = new CropThirdCateVO();
            BeanUtils.copyProperties(cropThirdCate, cropThirdCateVO);

            List<CropInfo> cropInfoList = cropInfoMapper.selectByCateId(cropThirdCate.getId());
            cropThirdCateVO.setCropInfoList(cropInfoList);
            cropThirdCateVOS.add(cropThirdCateVO);
        }
        return cropThirdCateVOS;
    }
}
