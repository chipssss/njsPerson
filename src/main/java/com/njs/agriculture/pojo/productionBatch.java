package com.njs.agriculture.pojo;

import java.util.Date;

public class productionBatch {
    private Integer id;

    private String name;

    private Integer cropInfoId;

    private Integer filedId;

    private Date plantTime;

    private Date collectTime;

    public productionBatch(Integer id, String name, Integer cropInfoId, Integer filedId, Date plantTime, Date collectTime) {
        this.id = id;
        this.name = name;
        this.cropInfoId = cropInfoId;
        this.filedId = filedId;
        this.plantTime = plantTime;
        this.collectTime = collectTime;
    }

    public productionBatch() {
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

    public Integer getFiledId() {
        return filedId;
    }

    public void setFiledId(Integer filedId) {
        this.filedId = filedId;
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