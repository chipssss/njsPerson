package com.njs.agriculture.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class RecoveryRecord {
    private Integer id;

    private String crop;

    private Integer fieldId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;

    private Integer source;

    private Integer sourceId;

    private String userName;

    public RecoveryRecord(Integer id, String crop, Integer fieldId, Date createTime, Integer source, Integer sourceId, String userName) {
        this.id = id;
        this.crop = crop;
        this.fieldId = fieldId;
        this.createTime = createTime;
        this.source = source;
        this.sourceId = sourceId;
        this.userName = userName;
    }

    public RecoveryRecord() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCrop() {
        return crop;
    }

    public void setCrop(String crop) {
        this.crop = crop == null ? null : crop.trim();
    }

    public Integer getFieldId() {
        return fieldId;
    }

    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }
}