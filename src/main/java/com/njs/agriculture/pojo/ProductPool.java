package com.njs.agriculture.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class ProductPool {
    private Integer id;

    private String name;

    private Float quantity;

    private String remark;

    private BigDecimal price;

    private Integer qrcodeId;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date productionTime;

    private Integer shelfLife;

    private Integer source;

    private Integer sourceId;

    private Integer categoryId;

    public ProductPool(Integer id, String name, Float quantity, String remark, BigDecimal price, Integer qrcodeId, Date productionTime, Integer shelfLife, Integer source, Integer sourceId, Integer categoryId) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.remark = remark;
        this.price = price;
        this.qrcodeId = qrcodeId;
        this.productionTime = productionTime;
        this.shelfLife = shelfLife;
        this.source = source;
        this.sourceId = sourceId;
        this.categoryId = categoryId;
    }

    public ProductPool() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQrcodeId() {
        return qrcodeId;
    }

    public void setQrcodeId(Integer qrcodeId) {
        this.qrcodeId = qrcodeId;
    }

    public Date getProductionTime() {
        return productionTime;
    }

    public void setProductionTime(Date productionTime) {
        this.productionTime = productionTime;
    }

    public Integer getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(Integer shelfLife) {
        this.shelfLife = shelfLife;
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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}