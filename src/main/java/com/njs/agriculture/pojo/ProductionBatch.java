package com.njs.agriculture.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ProductionBatch {
    private Integer id;

    private String name;

    private Integer cropInfoId;

    private Integer fieldId;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date plantTime;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date collectTime;

    public ProductionBatch(Integer id, String name, Integer cropInfoId, Integer fieldId, Date plantTime, Date collectTime) {
        this.id = id;
        this.name = name;
        this.cropInfoId = cropInfoId;
        this.fieldId = fieldId;
        this.plantTime = plantTime;
        this.collectTime = collectTime;
    }

    public ProductionBatch() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCropInfoId() {
        return cropInfoId;
    }

    public void setCropInfoId(Integer cropInfoId) {
        this.cropInfoId = cropInfoId;
    }

    public Integer getFieldId() {
        return fieldId;
    }

    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

    public Date getPlantTime() {
        return plantTime;
    }

    public void setPlantTime(Date plantTime) {
        this.plantTime = plantTime;
    }

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }
}