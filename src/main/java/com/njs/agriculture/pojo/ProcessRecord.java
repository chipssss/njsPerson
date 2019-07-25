package com.njs.agriculture.pojo;

import java.util.Date;

public class ProcessRecord {
    private Integer id;

    private Integer filedId;

    private String location;

    private Integer cropId;

    private String operation;

    private String inputRecord;

    private Date createTime;

    private String remark;

    private String weather;

    public ProcessRecord(Integer id, Integer filedId, String location, Integer cropId, String operation, String inputRecord, Date createTime, String remark, String weather) {
        this.id = id;
        this.filedId = filedId;
        this.location = location;
        this.cropId = cropId;
        this.operation = operation;
        this.inputRecord = inputRecord;
        this.createTime = createTime;
        this.remark = remark;
        this.weather = weather;
    }

    public ProcessRecord() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFiledId() {
        return filedId;
    }

    public void setFiledId(Integer filedId) {
        this.filedId = filedId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Integer getCropId() {
        return cropId;
    }

    public void setCropId(Integer cropId) {
        this.cropId = cropId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
    }

    public String getInputRecord() {
        return inputRecord;
    }

    public void setInputRecord(String inputRecord) {
        this.inputRecord = inputRecord == null ? null : inputRecord.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather == null ? null : weather.trim();
    }
}