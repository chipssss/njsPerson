package com.njs.agriculture.pojo;

import java.util.Date;

public class ProductActivation {
    private Integer id;

    private String code;

    private String batchId;

    private Integer userId;

    private Date createTime;

    private String productName;

    public ProductActivation(Integer id, String code, String batchId, Integer userId, Date createTime, String productName) {
        this.id = id;
        this.code = code;
        this.batchId = batchId;
        this.userId = userId;
        this.createTime = createTime;
        this.productName = productName;
    }

    public ProductActivation() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId == null ? null : batchId.trim();
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }
}