package com.njs.agriculture.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class InputEnterprise {
    private Integer id;

    private Integer categoryId;

    private String name;

    private Integer quantity;

    private String specification;

    private BigDecimal price;

    private Date productionTime;

    private Integer shelfLife;

    private String manufacturer;

    private String remark;

    private Date createTime;

    private Integer enterpriseId;

    public InputEnterprise(Integer id, Integer categoryId, String name, Integer quantity, String specification, BigDecimal price, Date productionTime, Integer shelfLife, String manufacturer, String remark, Date createTime, Integer enterpriseId) {
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
        this.enterpriseId = enterpriseId;
    }

    public InputEnterprise() {
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
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

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
}