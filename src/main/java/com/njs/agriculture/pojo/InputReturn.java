package com.njs.agriculture.pojo;

import java.util.Date;

public class InputReturn {
    private Integer id;

    private Integer userinputId;

    private Integer enterpriseinputId;

    private Integer quantity;

    private Date createTime;

    public InputReturn(Integer id, Integer userinputId, Integer enterpriseinputId, Integer quantity, Date createTime) {
        this.id = id;
        this.userinputId = userinputId;
        this.enterpriseinputId = enterpriseinputId;
        this.quantity = quantity;
        this.createTime = createTime;
    }

    public InputReturn() {
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}