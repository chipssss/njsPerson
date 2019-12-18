package com.njs.agriculture.service.impl;

import com.njs.agriculture.VO.MachineVO;
import com.njs.agriculture.bo.MachineBO;
import com.njs.agriculture.mapper.RootRecordDOMapper;
import com.njs.agriculture.pojo.Machining;
import com.njs.agriculture.pojo.RootRecordDO;
import com.njs.agriculture.service.IBatchCodeService;
import com.njs.agriculture.service.IRootRecordService;
import com.njs.agriculture.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * @author: chips
 * @date: 2019-12-17
 * @description: 溯源记录信息，n条种植记录+1/0条加工记录+1条包装记录
 **/
@Service("iRootService")
public class RootRecordServiceImpl implements IRootRecordService, IBatchCodeService {
    private static final String BATCH_PREFIX = "PC";
    @Autowired
    RootRecordDOMapper rootRecordDOMapper;

    @Override
    public void recordRoot(MachineBO machineBO) {
        RootRecordDO recordDO = machineBO.isMachining()? doMachiningRecord(machineBO): doPackRecord(machineBO);
        if (machineBO.isBatchIdNull()) {
            rootRecordDOMapper.insert(recordDO);
        } else {
            rootRecordDOMapper.updateByPrimaryKey(recordDO);
        }
    }

    /**
     * 包装记录分两种情况：1. 带批次码（说明root_record上已有记录），此时插入包装记录id即可；
     *                  2. 不带批次码，插入新的root_record，包装记录存id
     * @param machineBO
     * @return
     */
    private RootRecordDO doPackRecord(MachineBO machineBO) {
        RootRecordDO rootRecordDO;
        if (machineBO.isBatchIdNull()) {
            rootRecordDO = doMachiningRecord(machineBO);
            rootRecordDO.setMachineId(null);
        } else {
            rootRecordDO = rootRecordDOMapper.selectNewByBatchId(machineBO.getBatchId());
        }
        rootRecordDO.setPackId(machineBO.getId());
        return rootRecordDO;
    }

    private RootRecordDO doMachiningRecord(Machining machining) {
        int fieldId = machining.getFieldId();
        return new RootRecordDO(null, fieldId, generateCode(fieldId, machining.getSourceId()),
                getNextPlantStart(fieldId), machining.getCreateTime(), machining.getId(), null);
    }

    @Override
    public String generateCode(int fieldId, int userId) {
        StringBuilder builder = new StringBuilder(BATCH_PREFIX);
        builder.append(fieldId).append(userId).append(DateUtil.dateToStr(new Date(),"yyyyMMdd"))
            .append((int)(Math.random()*100));
        return builder.toString();
    }

    /**
     * 获取同一田块上的记录，
     * @param fieldId
     * @return 上一条批次截止时间+1min；如无上一批次，为空
     */
    private Date getNextPlantStart(int fieldId) {
        RootRecordDO rootRecordDO = rootRecordDOMapper.selectNewByFieldId(fieldId);
        if (rootRecordDO == null) return null;

        // 分钟+1
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(rootRecordDO.getPlantEnd());
        calendar.add(Calendar.MINUTE, 1);
        return calendar.getTime();
    }
}
