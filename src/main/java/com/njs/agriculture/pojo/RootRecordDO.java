package com.njs.agriculture.pojo;

import java.util.Date;

public class RootRecordDO {
    private Integer id;

    private Integer fieldId;

    private String batchNum;

    private Date plantStart;

    private Date plantEnd;

    private Integer machineId;

    private Integer packId;

    public RootRecordDO(Integer id, Integer fieldId, String batchNum, Date plantStart, Date plantEnd, Integer machineId, Integer packId) {
        this.id = id;
        this.fieldId = fieldId;
        this.batchNum = batchNum;
        this.plantStart = plantStart;
        this.plantEnd = plantEnd;
        this.machineId = machineId;
        this.packId = packId;
    }

    public RootRecordDO() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFieldId() {
        return fieldId;
    }

    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

    public String getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum == null ? null : batchNum.trim();
    }

    public Date getPlantStart() {
        return plantStart;
    }

    public void setPlantStart(Date plantStart) {
        this.plantStart = plantStart;
    }

    public Date getPlantEnd() {
        return plantEnd;
    }

    public void setPlantEnd(Date plantEnd) {
        this.plantEnd = plantEnd;
    }

    public Integer getMachineId() {
        return machineId;
    }

    public void setMachineId(Integer machineId) {
        this.machineId = machineId;
    }

    public Integer getPackId() {
        return packId;
    }

    public void setPackId(Integer packId) {
        this.packId = packId;
    }
}