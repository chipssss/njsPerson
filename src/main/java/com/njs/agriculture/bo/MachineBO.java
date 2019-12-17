package com.njs.agriculture.bo;

import com.njs.agriculture.VO.MachineVO;
import com.njs.agriculture.pojo.Machining;

import java.util.Date;

/**
 * @author: chips
 * @date: 2019-12-17
 * @description:
 **/
public class MachineBO extends Machining{
    private static final int MACHINING = 0;
    private String batchId;

    public MachineBO() {
    }

    public MachineBO(Integer id, Integer stockId, Integer quantity, Integer output, String level, String record, String inspector, String imageList, Date createTime, Integer source, Integer sourceId, Integer typeStatus, Integer fieldId, String batchId) {
        super(id, stockId, quantity, output, level, record, inspector, imageList, createTime, source, sourceId, typeStatus, fieldId);
        this.batchId = batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public boolean isMachining() {
        return getTypeStatus() == MACHINING;
    }

    public boolean isBatchIdNull() {
        return batchId == null;
    }

    public String getBatchId() {
        return batchId;
    }
}
