package com.njs.agriculture.pojo;

import java.util.Date;

public class ProductionBatch {
    private Integer id;

    private String name;

    private Integer fieldId;

    private Date plantTime;

    private Date collectTime;

    private Integer finish;

    private Integer generated;

    private Integer quantity;

    public ProductionBatch(Integer id, String name, Integer fieldId, Date plantTime, Date collectTime, Integer finish, Integer generated, Integer quantity) {
        this.id = id;
        this.name = name;
        this.fieldId = fieldId;
        this.plantTime = plantTime;
        this.collectTime = collectTime;
        this.finish = finish;
        this.generated = generated;
        this.quantity = quantity;
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

    public Integer getFinish() {
        return finish;
    }

    public void setFinish(Integer finish) {
        this.finish = finish;
    }

    public Integer getGenerated() {
        return generated;
    }

    public void setGenerated(Integer generated) {
        this.generated = generated;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}