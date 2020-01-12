package com.njs.agriculture.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.njs.agriculture.VO.*;
import com.njs.agriculture.common.Const;
import com.njs.agriculture.common.ServerResponse;
import com.njs.agriculture.mapper.*;
import com.njs.agriculture.pojo.*;
import com.njs.agriculture.service.IProductService;
import com.njs.agriculture.service.IRootRecordService;
import com.njs.agriculture.service.IUserService;
import com.njs.agriculture.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/8/2
 * @Description:
 */
@Slf4j
@Service("iProductService")
public class ProductServiceImpl implements IProductService {

    private static final Integer CATE_OTHER_ID = 0;
    @Autowired
    private SecureImageMapper secureImageMapper;
    @Autowired
    private ProductBasicMapper productBasicMapper;



    @Autowired
    private IUserService iUserService;

    @Autowired
    private ProductStockMapper productStockMapper;

    @Autowired
    private ProductOutMapper productOutMapper;

    @Autowired
    MachiningMapper machiningMapper;

    @Autowired
    MachineOperationMapper machineOperationMapper;

    @Autowired
    IRootRecordService iRootRecordService;

    @Autowired
    FieldMapper fieldMapper;


    @Autowired
    private ProductionFirstCateMapper productionFirstCateMapper;

    @Autowired
    private ProductionSecondCateMapper productionSecondCateMapper;

    @Autowired
    private ProductionThirdCateMapper productionThirdCateMapper;


    @Override
    public ServerResponse productionDel(int id, int flag) {
        int resultRow = 0;
        if (flag == 1) {
            resultRow = productionFirstCateMapper.deleteByPrimaryKey(id);
        } else if (flag == 2) {
            resultRow = productionSecondCateMapper.deleteByPrimaryKey(id);
        } else if (flag == 3) {
            resultRow = productionThirdCateMapper.deleteByPrimaryKey(id);
        } else if (flag == 0) {
            resultRow = productBasicMapper.deleteByPrimaryKey(id);
        }
        if (resultRow == 0) {
            return ServerResponse.createByErrorMessage("删除失败！");
        }
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse productBasicAdd(ProductBasic productBasic, int userId) {
        ServerResponse<Map> serverResponse = iUserService.isManager(userId);

        productBasic.setSource((int) serverResponse.getData().get("source"));
        productBasic.setSourceId((int) serverResponse.getData().get("sourceId"));
        productBasic.setTotalSale(0);
        productBasic.setTotalStock(0);

        int resultRow = productBasicMapper.insert(productBasic);
        if (resultRow == 0) {
            return ServerResponse.createByErrorMessage("插入新数据失败！");
        }
        return ServerResponse.createBySuccess(productBasic);
    }

    @Override
    public ServerResponse productBasicUpdate(ProductBasic productBasic) {
        int resultRow = productBasicMapper.updateByPrimaryKeySelective(productBasic);
        if (resultRow == 0) {
            return ServerResponse.createByErrorMessage("更新失败！");
        }
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse productBasicGet(int userId) {
        ServerResponse<Map> serverResponse = iUserService.isManager(userId);
        List<ProductBasic> productBasicList = productBasicMapper.selectBySource((int) serverResponse.getData().get("source"), (int) serverResponse.getData().get("sourceId"));
        return ServerResponse.createBySuccess(productBasic2VO(productBasicList));
    }

    public List<ProductBasicVO> productBasic2VO(List<ProductBasic> productBasicList){
        List<ProductBasicVO> productBasicVOList = Lists.newLinkedList();
        if(productBasicList == null || productBasicList.isEmpty()){
            return productBasicVOList;
        }
        for (ProductBasic productBasic : productBasicList) {
            productBasicVOList.add(productBasic2VO(productBasic));
        }
        return productBasicVOList;
    }

    /* 单个产品类别的VO转化 */
    private ProductBasicVO productBasic2VO(ProductBasic productBasic) {
        ProductBasicVO productBasicVO = new ProductBasicVO();
        BeanUtils.copyProperties(productBasic, productBasicVO);
        ProductionThirdCate productionThirdCate = productionThirdCateMapper.selectByPrimaryKey(productBasic.getTypeId());

        if(productionThirdCate == null){
            return productBasicVO;
        }

        // 当类别为其他时，不继续检索二级类别
        if (productionThirdCate.getSecondcateId().equals(CATE_OTHER_ID)) {
            productBasicVO.setCateInfo(productionThirdCate.getName());
            return productBasicVO;
        }

        ProductionSecondCate productionSecondCate = productionSecondCateMapper.selectByPrimaryKey(productionThirdCate.getSecondcateId());
        if(productionSecondCate == null){
            return productBasicVO;
        }
        ProductionFirstCate productionFirstCate = productionFirstCateMapper.selectByPrimaryKey(productionSecondCate.getFirstcateId());
        if(productionFirstCate == null){
            return productBasicVO;
        }
        productBasicVO.setCateInfo(productionFirstCate.getName() + "|" + productionSecondCate.getName() + "|" + productionThirdCate.getName());
        return productBasicVO;
    }

    @Override
    public ServerResponse productStockAdd(ProductStockVO productStockVO, int userId) {

        ServerResponse<Map> serverResponse = iUserService.isManager(userId);
        int source = (int) serverResponse.getData().get("source");
        int sourceId = (int) serverResponse.getData().get("sourceId");
        productStockVO.setSource(source);
        productStockVO.setSourceId(sourceId);
        StringBuilder batchId = new StringBuilder();
        if(source == 0){
            batchId.append("GR");
        }else {
            batchId.append("DW");
        }
        batchId.append(sourceId).append(productStockVO.getProductId()).append(DateUtil.dateToStr(new Date(),"yyyyMMdd"));
        productStockVO.setBatchId(batchId.toString());
        ProductStock productStock=new ProductStock();
       BeanUtils.copyProperties(productStockVO,productStock);
        int resultRow = productStockMapper.insert(productStock);
        Integer id=productStock.getId();

      SecureImage secureImage=new SecureImage();
      secureImage.setId(id);
        for(String Image:productStockVO.getSecureImage()) {
            secureImage.setSecureImage(Image);
        secureImageMapper.insert(secureImage);
        }
        if (resultRow == 0) {
            return ServerResponse.createByErrorMessage("插入新数据失败！");
        }

        //更新基础表
        updateBasicTotal(productStockVO.getProductId(), productStockVO.getQuantity(), 0);
        return ServerResponse.createBySuccess(productStockVO);
    }

    @Override
    public ServerResponse productStockGet(int userId) {
        ServerResponse<Map> serverResponse = iUserService.isManager(userId);
        List<ProductStock> productStockList;
        List<ProductStockVO> productStockVOList=Lists.newLinkedList();
        productStockList = productStockMapper.selectBySource((int) serverResponse.getData().get("source"), (int) serverResponse.getData().get("sourceId"));
        for(ProductStock productStocktemp:productStockList){
            ProductStockVO productStockVO=new ProductStockVO();
            BeanUtils.copyProperties(productStocktemp,productStockVO);


            productStockVO.setSecureImage( secureImageMapper.selectById(productStocktemp.getId()));
            productStockVOList.add(productStockVO);
        }
        return ServerResponse.createBySuccess(productStockVOList);
    }

    @Override
    public ServerResponse productStockGetByProductId(int userId, int productId) {
        ServerResponse<Map> serverResponse = iUserService.isManager(userId);
        List<ProductStock> productStockList;

        productStockList = productStockMapper.selectBySourceAndProductId((int) serverResponse.getData().get("source"), (int) serverResponse.getData().get("sourceId"), productId);

        return ServerResponse.createBySuccess(productStockList);
    }

    @Override
    @Transactional
    public ServerResponse productOut(ProductOut productOut, int userId) {
        ProductStock productStock = productStockMapper.selectByPrimaryKey(productOut.getStockId());
        if (productStock == null) {
            return ServerResponse.createByErrorMessage("库存不存在");
        }
        int result = productStock.getQuantity() - productOut.getQuantity();
        if (productOut.getQuantity() <= 0 || result < 0) {
            return ServerResponse.createByErrorMessage("输入数量错误！");
        }

        productStock.setQuantity(result);
        productStockMapper.updateByPrimaryKeySelective(productStock);

        ServerResponse<Map> serverResponse = iUserService.isManager(userId);
        productOut.setSource((int) serverResponse.getData().get("source"));
        productOut.setSourceId((int) serverResponse.getData().get("sourceId"));

        productOut.setProductId(productStock.getProductId());

        productOutMapper.insert(productOut);

        //更新基础表
        updateBasicTotal(productStock.getProductId(), productOut.getQuantity(), 1);

        return ServerResponse.createBySuccess(productOut);
    }

    @Override
    public ServerResponse productOutGetBySource(int userId) {
        ServerResponse<Map> serverResponse = iUserService.isManager(userId);
        int source = (int) serverResponse.getData().get("source");
        int sourceId = (int) serverResponse.getData().get("sourceId");

        PageHelper pageHelper = new PageHelper();
        pageHelper.orderBy("create_time desc");
        List<ProductOut> productOutList = productOutMapper.selectBySource(source, sourceId);
        return ServerResponse.createBySuccess(productOut2productOutVO(productOutList));
    }

    @Override
    public ServerResponse productOutGetByProductId(int productId) {
        PageHelper pageHelper = new PageHelper();
        pageHelper.orderBy("create_time desc");
        return ServerResponse.createBySuccess(productOut2productOutVO(productOutMapper.selectByProductId(productId)));
    }

    @Override
    @Transactional
    public ServerResponse machineAdd(MachineVO machineVO, int userId) {
        Map map = iUserService.isManager(userId).getData();
        machineVO.setSource((int) map.get("source"));
        machineVO.setSourceId((int) map.get("sourceId"));
        Machining machining = machineVO.converTOMachining();
        if(machineVO.getQuantity() < 0){
            return ServerResponse.createByErrorMessage("数量不能为负数！");
        }
        int resultRow = machiningMapper.insert(machining);
        if (resultRow > 0) {
            // 登记溯源信息
            machineVO.setId(machining.getId()); // 记录插入后生成的id
            iRootRecordService.insertRecordRoot(machineVO);
        }
        return ServerResponse.createByResultRow(resultRow);
    }

    @Override
    public ServerResponse machineGet(int userId) {
        Map map = iUserService.isManager(userId).getData();
        PageHelper pageHelper = new PageHelper();
        pageHelper.orderBy("create_time desc");
        List<Machining> machiningList = machiningMapper.selectBySource((int) map.get("source"), (int) map.get("sourceId"));
        return ServerResponse.createBySuccess(machine2MachineVO(machiningList));
    }

    public List<MachineVO> machine2MachineVO(List<Machining> machiningList){
        List<MachineVO> machineVOList = Lists.newLinkedList();
        for (Machining machining : machiningList) {
            machineVOList.add(MachineVO.convertFor(machining));
        }
        // 获取田块名称
        machineVOList.stream().forEach(machineVO -> {
            if (machineVO.getFieldId() != null) {
                Field field = fieldMapper.selectByPrimaryKey(machineVO.getFieldId());
                if (field != null) {
                    machineVO.setFieldName(field.getName());
                }
            }
        });
        return machineVOList;
    }

    @Override
    public ServerResponse getAllStream(int userId) {
        // TODO 多线程获取结果
        List<StreamVO> streamVOList = Lists.newLinkedList();
        List<ProductStock> stockList = (List) productStockGet(userId).getData();
        for (ProductStock productStock : stockList) {
            StreamVO streamVO = new StreamVO();
            streamVO.setOperation(Const.StreamOperation.INSTOCK);
            /*streamVO.setProductId(productStock.getProductId());
            streamVO.setBatchId(productStock.getBatchId());
            streamVO.setQuantity(productStock.getQuantity());
            streamVO.setCreateTime(productStock.getCreateTime());*/
            BeanUtils.copyProperties(productStock, streamVO);
            ProductBasic productBasic = productBasicMapper.selectByPrimaryKey(productStock.getProductId());
            if (productBasic == null) {
                continue;
            }
            streamVO.setProductName(productBasic.getName());
            streamVOList.add(streamVO);
        }
        List<MachineVO> machineVOList = (List) machineGet(userId).getData();
        for (MachineVO machineVO : machineVOList) {
            StreamVO streamVO = new StreamVO();
            if (machineVO.getRecord() == null) {
                continue;
            }
            if (machineVO.getRecord().contains(Const.MachineOperation.INPUT)) {
                streamVO.setOperation(Const.StreamOperation.INPUT);
            } else if (machineVO.getRecord().contains(Const.MachineOperation.OUTPUT)) {
                streamVO.setOperation(Const.MachineOperation.OUTPUT);
            } else {
                continue;
            }
            ProductStock productStock = productStockMapper.selectByPrimaryKey(machineVO.getStockId());
            ProductBasic productBasic = productBasicMapper.selectByPrimaryKey(productStock.getProductId());
            if (productBasic == null || productStock == null) {
                continue;
            }
            streamVO.setProductName(productBasic.getName());
            streamVO.setProductId(productStock.getProductId());
            streamVO.setBatchId(productStock.getBatchId());
            streamVO.setQuantity(machineVO.getQuantity());
            streamVO.setCreateTime(machineVO.getCreateTime());
            streamVOList.add(streamVO);
        }
        List<ProductOut> productOutList = (List) productOutGetBySource(userId).getData();
        for (ProductOut productOut : productOutList) {
            StreamVO streamVO = new StreamVO();
            streamVO.setOperation(Const.StreamOperation.SALE);
            ProductStock productStock = productStockMapper.selectByPrimaryKey(productOut.getStockId());
            if(productStock == null){
                continue;
            }
            ProductBasic productBasic = productBasicMapper.selectByPrimaryKey(productStock.getProductId());
            if (productBasic == null) {
                continue;
            }
            streamVO.setProductName(productBasic.getName());
            streamVO.setProductId(productStock.getProductId());
            streamVO.setBatchId(productStock.getBatchId());
            streamVO.setQuantity(productOut.getQuantity());
            streamVO.setCreateTime(productOut.getCreateTime());
            streamVOList.add(streamVO);
        }
        return ServerResponse.createBySuccess(streamVOList);
    }

    @Override
    public ServerResponse getAllMachineOperation() {
        List<String> operations = machineOperationMapper.selectAll();
        return ServerResponse.createBySuccess(operations);
    }

    @Override
    public ServerResponse getTeaStock(int userId) {
        List<ProductBasic> productBasicList = productBasicMapper.selectByProductType(Const.ProductType.CHAQING);
        return getStock(productBasicList, userId);
    }

    @Override
    public ServerResponse getAllBatchInfo(int userId) {
        List<ProductBasic> productBasicList = productBasicMapper.selectAll();
        return getStock(productBasicList, userId);
    }

    @Override
    public ProductBasicVO getById(int productId) {
        ProductBasic basic = productBasicMapper.selectByPrimaryKey(productId);
        if (basic == null) {
            return null;
        }
        return productBasic2VO(basic);
    }

    private ServerResponse getStock(List<ProductBasic> productBasicList, int userId){
        Map user = iUserService.isManager(userId).getData();
        List<Map> mapList = Lists.newLinkedList();
        for (ProductBasic productBasic : productBasicList) {
            Map map = Maps.newHashMap();
            map.put("value", productBasic.getName());
            List<ProductStock> stockList = productStockMapper.selectByProductIdAndSource(productBasic.getId(), (int)user.get("source"), (int)user.get("sourceId"));
            map.put("stockList", stockList);
            mapList.add(map);
        }
        return ServerResponse.createBySuccess(mapList);
    }


    public List<ProductOutVO> productOut2productOutVO(List<ProductOut> productOutList) {
        List<ProductOutVO> productOutVOList = Lists.newLinkedList();
        for (ProductOut productOut : productOutList) {
            ProductOutVO productOutVO = new ProductOutVO();
            BeanUtils.copyProperties(productOut, productOutVO);
            productOutVOList.add(productOutVO);
            ProductBasic productBasic = productBasicMapper.selectByPrimaryKey(productOut.getProductId());
            ProductStock productStock = productStockMapper.selectByPrimaryKey(productOut.getStockId());
            if (productBasic != null) {
                productOutVO.setProductName(productBasic.getName());
            }
            if (productStock != null) {
                productOutVO.setBatchNum(productStock.getBatchId());
            }
        }
        return productOutVOList;
    }

    /**
     * 加了锁的对基本表的操作，防止并发
     *
     * @param
     * @param type 0的时候为加库存，1的时候为减库存，加销售
     */
    public synchronized void updateBasicTotal(int basicId, int quantity, int type) {
        ProductBasic productBasic = productBasicMapper.selectByPrimaryKey(basicId);
        if (productBasic == null) {
            throw new RuntimeException("查不到基本表数据！");
        }
        ProductBasic productBasicNew = new ProductBasic();
        productBasicNew.setId(productBasic.getId());
        if (type == 0) {
            int totalStock = productBasic.getTotalStock() + quantity;
            productBasicNew.setTotalStock(totalStock);
        } else {
            int totalStock = productBasic.getTotalStock() - quantity;
            if(totalStock < 0){
                log.error("总库存不够，帐对不上，basicId={], quantity={}", basicId, quantity);
            }
            productBasicNew.setTotalStock(totalStock);
            int totalSale = productBasic.getTotalSale() + quantity;
            productBasicNew.setTotalSale(totalSale);
        }

        productBasicMapper.updateByPrimaryKeySelective(productBasicNew);
    }

}
