package com.njs.agriculture.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class Machining {
    private Integer id;

    private Integer stockId;

    private Integer quantity;

    private Integer output;

    private String level;

    private String record;

    private String inspector;

    @JsonIgnore
    private String imageList;

    private Date createTime;

    private Integer source;

    private Integer sourceId;

    public Machining(Integer id, Integer stockId, Integer quantity, Integer output, String level, String record, String inspector, String imageList, Date createTime, Integer source, Integer sourceId) {
        this.id = id;
        this.stockId = stockId;
        this.quantity = quantity;
        this.output = output;
        this.level = level;
        this.record = record;
        this.inspector = inspector;
        this.imageList = imageList;
        this.createTime = createTime;
        this.source = source;
        this.sourceId = sourceId;
    }

    public Machining() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getOutput() {
        return output;
    }

    public void setOutput(Integer output) {
        this.output = output;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record == null ? null : record.trim();
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector == null ? null : inspector.trim();
    }

    public String getImageList() {
        return imageList;
    }

    public void setImageList(String imageList) {
        this.imageList = imageList == null ? null : imageList.trim();
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
}