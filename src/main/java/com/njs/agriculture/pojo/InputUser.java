package com.njs.agriculture.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class InputUser {
    private Integer id;

    private Integer categoryId;

    private String name;

    private Float quantity;

    private String specification;

    private BigDecimal price;

    private Date productionTime;

    private Integer shelfLife;

    private String manufacturer;

    private String remark;

    private Date createTime;

    private Integer userId;

    //来源,默认为0用户添加，1企业领用
    private Integer source;

    private Integer sourceId;

    public InputUser(Integer id, Integer categoryId, String name, Float quantity, String specification, BigDecimal price, Date productionTime, Integer shelfLife, String manufacturer, String remark, Date createTime, Integer userId, Integer source, Integer sourceId) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.quantity = quantity;
        this.specification = specification;
        this.price = price;
        this.productionTime = productionTime;
        this.shelfLife = shelfLife;
        this.manufacturer = manufacturer;
        this.remark = remark;
        this.createTime = createTime;
        this.userId = userId;
        this.source = source;
        this.sourceId = sourceId;
    }

    public InputUser() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification == null ? null : specification.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer == null ? null : manufacturer.trim();
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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