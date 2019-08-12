package com.njs.agriculture.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ProductStock {
    private Integer id;

    private Integer productId;

    private Integer quantity;

    private Date productionTime;

    private String productionMessage;

    private Integer shelfLife;

    private Integer batchId;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm",timezone = "GMT+8")
    private Date createTime;

    private String secureImage;

    private Integer source;

    private Integer sourceId;

    public ProductStock(Integer id, Integer productId, Integer quantity, Date productionTime, String productionMessage, Integer shelfLife, Integer batchId, Date createTime, String secureImage, Integer source, Integer sourceId) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.productionTime = productionTime;
        this.productionMessage = productionMessage;
        this.shelfLife = shelfLife;
        this.batchId = batchId;
        this.createTime = createTime;
        this.secureImage = secureImage;
        this.source = source;
        this.sourceId = sourceId;
    }

    public ProductStock() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getProductionTime() {
        return productionTime;
    }

    public void setProductionTime(Date productionTime) {
        this.productionTime = productionTime;
    }

    public String getProductionMessage() {
        return productionMessage;
    }

    public void setProductionMessage(String productionMessage) {
        this.productionMessage = productionMessage == null ? null : productionMessage.trim();
    }

    public Integer getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(Integer shelfLife) {
        this.shelfLife = shelfLife;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSecureImage() {
        return secureImage;
    }

    public void setSecureImage(String secureImage) {
        this.secureImage = secureImage == null ? null : secureImage.trim();
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