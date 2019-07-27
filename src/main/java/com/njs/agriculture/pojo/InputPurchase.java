package com.njs.agriculture.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class InputPurchase {
    private Integer id;

    private Integer inputSource;

    private Integer sourceId;

    private Float quantity;

    private Date createTime;

    private BigDecimal price;

    public InputPurchase(Integer id, Integer inputSource, Integer sourceId, Float quantity, Date createTime, BigDecimal price) {
        this.id = id;
        this.inputSource = inputSource;
        this.sourceId = sourceId;
        this.quantity = quantity;
        this.createTime = createTime;
        this.price = price;
    }

    public InputPurchase() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInputSource() {
        return inputSource;
    }

    public void setInputSource(Integer inputSource) {
        this.inputSource = inputSource;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}