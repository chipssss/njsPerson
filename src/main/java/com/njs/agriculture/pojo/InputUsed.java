package com.njs.agriculture.pojo;

import java.util.Date;

public class InputUsed {
    private Integer id;

    private Integer source;

    private Integer sourceId;

    private Float quantity;

    private Date createTime;

    public InputUsed(Integer id, Integer source, Integer sourceId, Float quantity, Date createTime) {
        this.id = id;
        this.source = source;
        this.sourceId = sourceId;
        this.quantity = quantity;
        this.createTime = createTime;
    }

    public InputUsed() {
        super();
    }

    public InputUsed(Integer source, Integer sourceId, Float quantity) {
        this.source = source;
        this.sourceId = sourceId;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}