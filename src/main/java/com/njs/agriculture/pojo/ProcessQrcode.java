package com.njs.agriculture.pojo;

public class ProcessQrcode {
    private Integer id;

    private Integer qrcodeId;

    private Integer recordId;

    public ProcessQrcode(Integer id, Integer qrcodeId, Integer recordId) {
        this.id = id;
        this.qrcodeId = qrcodeId;
        this.recordId = recordId;
    }

    public ProcessQrcode(Integer qrcodeId, Integer recordId) {
        this.qrcodeId = qrcodeId;
        this.recordId = recordId;
    }

    public ProcessQrcode() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQrcodeId() {
        return qrcodeId;
    }

    public void setQrcodeId(Integer qrcodeId) {
        this.qrcodeId = qrcodeId;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }
}