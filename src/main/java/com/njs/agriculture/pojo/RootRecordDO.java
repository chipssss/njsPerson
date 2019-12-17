package com.njs.agriculture.pojo;

import java.util.Date;

public class RootRecordDO {
    private Integer id;

    private Integer fieldId;

    private String batchNum;

    private Date plantStart;

    private Date plantEnd;

    private Date machineStart;

    private Date machineEnd;

    public RootRecordDO(Integer id, Integer fieldId, String batchNum, Date plantStart, Date plantEnd, Date machineStart, Date machineEnd) {
        this.id = id;
        this.fieldId = fieldId;
        this.batchNum = batchNum;
        this.plantStart = plantStart;
        this.plantEnd = plantEnd;
        this.machineStart = machineStart;
        this.machineEnd = machineEnd;
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

    public Date getMachineStart() {
        return machineStart;
    }

    public void setMachineStart(Date machineStart) {
        this.machineStart = machineStart;
    }

    public Date getMachineEnd() {
        return machineEnd;
    }

    public void setMachineEnd(Date machineEnd) {
        this.machineEnd = machineEnd;
    }
}