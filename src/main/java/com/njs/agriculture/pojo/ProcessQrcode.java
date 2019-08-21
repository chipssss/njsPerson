package com.njs.agriculture.pojo;

public class ProcessQrcode {
    private Integer id;

    private Integer batchId;

    private Integer recordId;

    public ProcessQrcode(Integer id, Integer batchId, Integer recordId) {
        this.id = id;
        this.batchId = batchId;
        this.recordId = recordId;
    }

    public ProcessQrcode() {
        super();
    }

    public ProcessQrcode(Integer batchId, Integer recordId) {
        this.batchId = batchId;
        this.recordId = recordId;
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

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }
}