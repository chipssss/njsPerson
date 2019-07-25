package com.njs.agriculture.pojo;

import java.util.Date;

public class ProcessQrcode {
    private Integer id;

    private Integer cropId;

    private Integer filerId;

    private Integer userId;

    private Date createTime;

    public ProcessQrcode(Integer id, Integer cropId, Integer filerId, Integer userId, Date createTime) {
        this.id = id;
        this.cropId = cropId;
        this.filerId = filerId;
        this.userId = userId;
        this.createTime = createTime;
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

    public Integer getCropId() {
        return cropId;
    }

    public void setCropId(Integer cropId) {
        this.cropId = cropId;
    }

    public Integer getFilerId() {
        return filerId;
    }

    public void setFilerId(Integer filerId) {
        this.filerId = filerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}