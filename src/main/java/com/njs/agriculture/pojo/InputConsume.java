package com.njs.agriculture.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class InputConsume {
    private Integer id;

    private Integer userinputId;

    private Integer enterpriseinputId;

    private Float quantity;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    private Integer status;

    private Integer enterpriseId;

    private Integer userId;

    public InputConsume(Integer id, Integer userinputId, Integer enterpriseinputId, Float quantity, Date createTime, Integer status, Integer enterpriseId, Integer userId) {
        this.id = id;
        this.userinputId = userinputId;
        this.enterpriseinputId = enterpriseinputId;
        this.quantity = quantity;
        this.createTime = createTime;
        this.status = status;
        this.enterpriseId = enterpriseId;
        this.userId = userId;
    }

    public InputConsume() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserinputId() {
        return userinputId;
    }

    public void setUserinputId(Integer userinputId) {
        this.userinputId = userinputId;
    }

    public Integer getEnterpriseinputId() {
        return enterpriseinputId;
    }

    public void setEnterpriseinputId(Integer enterpriseinputId) {
        this.enterpriseinputId = enterpriseinputId;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}