package com.njs.agriculture.pojo;

import com.njs.agriculture.VO.ProcessRecordInfoVO;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public class ProcessRecord {
    private Integer id;

    private Integer fieldId;

    private String location;

    private Integer cropId;

    private String operation;

    private String inputRecord;

    private Date createTime;

    private String remark;

    private String weather;

    private Integer source;

    private Integer sourceId;

    private Integer status;

    private String companyName;

    public ProcessRecord(Integer id, String inputRecord) {
        this.id = id;
        this.inputRecord = inputRecord;
    }

    public ProcessRecord(Integer id, Integer fieldId, String location, Integer cropId, String operation, String inputRecord, Date createTime, String remark, String weather, Integer source, Integer sourceId, Integer status) {
        this.id = id;
        this.fieldId = fieldId;
        this.location = location;
        this.cropId = cropId;
        this.operation = operation;
        this.inputRecord = inputRecord;
        this.createTime = createTime;
        this.remark = remark;
        this.weather = weather;
        this.source = source;
        this.sourceId = sourceId;
        this.status = status;
    }

    public ProcessRecord(Integer id, Integer fieldId, String location, Integer cropId, String operation, String inputRecord, Date createTime, String remark, String weather, Integer source, Integer sourceId, Integer status, String companyName) {
        this.id = id;
        this.fieldId = fieldId;
        this.location = location;
        this.cropId = cropId;
        this.operation = operation;
        this.inputRecord = inputRecord;
        this.createTime = createTime;
        this.remark = remark;
        this.weather = weather;
        this.source = source;
        this.sourceId = sourceId;
        this.status = status;
        this.companyName = companyName;
    }

    public ProcessRecord() {
        super();
    }

    /**
     * 开放接口：隐藏非必要字段
     */
    public void hideUnnecessary() {
        id = null;
        fieldId = null;
        cropId = null;
        inputRecord = null;
        source = null;
        sourceId = null;
        status = null;
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

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCompanyName() {
        return companyName;
    }

    /**
     * 格式化投入品记录
     * @param inputList
     */
    public void setInputRecord(List<ProcessRecordInfoVO.Input> inputList) {
        if (inputList == null || inputList.size() == 0) {
            return;
        }
        StringBuilder builder = new StringBuilder();
        inputList.forEach(input -> builder.append(format(input)).append(","));
        builder.setLength(builder.length()-1);
        inputRecord = builder.toString();
    }

    private String format(ProcessRecordInfoVO.Input input) {
        return input.getQuantity() == 0? input.getName(): String.format("%s %.1fkg",input.getName(), input.getQuantity());
    }

    @Override
    public String toString() {
        return "ProcessRecord{" +
                "id=" + id +
                ", fieldId=" + fieldId +
                ", location='" + location + '\'' +
                ", cropId=" + cropId +
                ", operation='" + operation + '\'' +
                ", inputRecord='" + inputRecord + '\'' +
                ", createTime=" + createTime +
                ", remark='" + remark + '\'' +
                ", weather='" + weather + '\'' +
                ", source=" + source +
                ", sourceId=" + sourceId +
                ", status=" + status +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}