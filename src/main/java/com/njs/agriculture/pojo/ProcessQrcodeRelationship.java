package com.njs.agriculture.pojo;

public class ProcessQrcodeRelationship {
    private Integer id;

    private Integer recordId;

    private Integer qrcodeId;

    public ProcessQrcodeRelationship(Integer id, Integer recordId, Integer qrcodeId) {
        this.id = id;
        this.recordId = recordId;
        this.qrcodeId = qrcodeId;
    }

    public ProcessQrcodeRelationship() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getQrcodeId() {
        return qrcodeId;
    }

    public void setQrcodeId(Integer qrcodeId) {
        this.qrcodeId = qrcodeId;
    }
}