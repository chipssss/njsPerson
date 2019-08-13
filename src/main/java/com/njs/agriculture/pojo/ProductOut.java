package com.njs.agriculture.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ProductOut {
    private Integer id;

    private Integer stockId;

    private Integer quantity;

    private Integer purchaserId;

    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createTime;

    private Integer source;

    private Integer sourceId;

    private Integer productId;

    public ProductOut(Integer id, Integer stockId, Integer quantity, Integer purchaserId, String remark, Date createTime, Integer source, Integer sourceId, Integer productId) {
        this.id = id;
        this.stockId = stockId;
        this.quantity = quantity;
        this.purchaserId = purchaserId;
        this.remark = remark;
        this.createTime = createTime;
        this.source = source;
        this.sourceId = sourceId;
        this.productId = productId;
    }

    public ProductOut() {
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

    public Integer getPurchaserId() {
        return purchaserId;
    }

    public void setPurchaserId(Integer purchaserId) {
        this.purchaserId = purchaserId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}