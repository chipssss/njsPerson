package com.njs.agriculture.pojo;

import java.util.Date;

public class InputStream {
    private Integer id;

    private Integer fieldId;

    private Integer cropId;

    private String inputName;

    private Integer quantity;

    private Date createTime;

    private Integer source;

    private Integer sourceId;

    private Integer recordId;

    public InputStream(Integer id, Integer recordId) {
        this.id = id;
        this.recordId = recordId;
    }

    public InputStream(Integer id, Integer fieldId, Integer cropId, String inputName, Integer quantity, Date createTime, Integer source, Integer sourceId, Integer recordId) {
        this.id = id;
        this.fieldId = fieldId;
        this.cropId = cropId;
        this.inputName = inputName;
        this.quantity = quantity;
        this.createTime = createTime;
        this.source = source;
        this.sourceId = sourceId;
        this.recordId = recordId;
    }

    public InputStream(String inputName, Integer quantity, Date createTime) {
        this.inputName = inputName;
        this.quantity = quantity;
        this.createTime = createTime;
    }

    public InputStream() {
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

    public Integer getCropId() {
        return cropId;
    }

    public void setCropId(Integer cropId) {
        this.cropId = cropId;
    }

    public String getInputName() {
        return inputName;
    }

    public void setInputName(String inputName) {
        this.inputName = inputName == null ? null : inputName.trim();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }
}