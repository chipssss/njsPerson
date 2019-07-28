package com.njs.agriculture.pojo;

import java.util.Date;

public class ProcessRecord {
    private Integer id;

    private Integer batchId;

    private String location;

    private Integer cropId;

    private String operation;

    private String inputRecord;

    private Date createTime;

    private String remark;

    private String weather;

    private Integer source;

    private Integer sourceId;

    public ProcessRecord(Integer id, Integer batchId, String location, Integer cropId, String operation, String inputRecord, Date createTime, String remark, String weather, Integer source, Integer sourceId) {
        this.id = id;
        this.batchId = batchId;
        this.location = location;
        this.cropId = cropId;
        this.operation = operation;
        this.inputRecord = inputRecord;
        this.createTime = createTime;
        this.remark = remark;
        this.weather = weather;
        this.source = source;
        this.sourceId = sourceId;
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

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
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

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getSourceid() {
        return sourceId;
    }

    public void setSourceid(Integer sourceId) {
        this.sourceId = sourceId;
    }
}